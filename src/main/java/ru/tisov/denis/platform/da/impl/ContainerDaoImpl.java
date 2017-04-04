package ru.tisov.denis.platform.da.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.ExposedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.async.callbacks.LogCallback;
import ru.tisov.denis.platform.async.callbacks.StartAfterPullImageCallback;
import ru.tisov.denis.platform.config.JVMConfigurator;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.da.EnvironmentDao;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.domain.*;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.domain.docker.Log;
import ru.tisov.denis.platform.enums.Hosts;
import ru.tisov.denis.platform.enums.Ports;
import ru.tisov.denis.platform.service.HostService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
public class ContainerDaoImpl implements ContainerDao {

    private static final Logger logger = LoggerFactory.getLogger(ContainerDaoImpl.class);
    private final DockerClientFactory dockerClientFactory;
    private final HostService hostService;
    private final EnvironmentDao environmentDao;
    private final JVMConfigurator jvmConfigurator;

    @Resource(name = "logQueue")
    private BlockingQueue<Log> logQueue;

    @Autowired
    public ContainerDaoImpl(DockerClientFactory dockerClientFactory, HostService hostService, EnvironmentDao environmentDao, JVMConfigurator jvmConfigurator) {
        this.dockerClientFactory = dockerClientFactory;
        this.hostService = hostService;
        this.environmentDao = environmentDao;
        this.jvmConfigurator = jvmConfigurator;
    }

    @Override
    public void start(Container container) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(container.getHostName());
        dockerClient.startContainerCmd(container.getId()).exec();
    }

    @Override
    public void stop(Container container) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(container.getHostName());
        try {
            dockerClient.stopContainerCmd(container.getId()).exec();
        } catch (NotModifiedException e) {
            logger.info("Container already stopped.", e);
        }
    }

    @Override
    public void remove(Container container) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(container.getHostName());
        dockerClient.removeContainerCmd(container.getId()).exec();
    }

    @Override
    public void restart(Container container) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(container.getHostName());
        dockerClient.restartContainerCmd(container.getId()).exec();
    }

    @Override
    public void loadLogs(Container container) {
        String hostName = container.getHostName();
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        String containerId = container.getId();
        dockerClient.logContainerCmd(containerId).withStdErr(true).withStdOut(true).withTail(200).exec(new LogCallback(logQueue, hostName, containerId));
    }

    @Override
    public void create(Container container, boolean startAfterCreate) {
        String hostName = container.getHostName();
        Integer port = container.getPort();

        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        Host currentHost = hostService.getByName(hostName);

        com.github.dockerjava.api.model.Ports portsBinding = bindPorts(port);

        String registryIp = getRegistryIp(dockerClient.authConfig().getRegistryAddress(), currentHost);

        String fullImageName = registryIp + ":" + Ports.REGISTRY_PORT.getPort() + "/" + container.getImageName();
        Environment environment = environmentDao.getById(container.getEnvironmentId());

        try {
            dockerClient.listImagesCmd().exec().forEach(image -> dockerClient.removeImageCmd(image.getId()).withForce(true).exec());
        } catch (ConflictException ex) {
            logger.info("Unable to delete image", ex);
        } finally {
            StartContainerParams params = new StartContainerParams(fullImageName, container.getName());

            params.setHost(currentHost);
            params.setEnvironment(environment);
            params.setPortsBinding(portsBinding);

            List<Network> networks = environment.getNetworks();
            if (!networks.isEmpty())
                params.setNetworkName(networks.get(0).getName());

            dockerClient.pullImageCmd(fullImageName).exec(new StartAfterPullImageCallback(startAfterCreate, dockerClient, params, jvmConfigurator));
        }
    }

    private String getRegistryIp(String registryAddress, Host currentHost) {
        String registryIp = registryAddress.split("//")[1].split(":")[0];
        if (registryIp.equals(currentHost.getUrl())) {
            registryIp = Hosts.LOCAL_HOST.getIp();
        }
        return registryIp;
    }

    private com.github.dockerjava.api.model.Ports bindPorts(Integer port) {
        if (port == null) return null;
        com.github.dockerjava.api.model.Ports portsBinding = new com.github.dockerjava.api.model.Ports();
        ExposedPort exposedPort = ExposedPort.tcp(Ports.DEFAULT_PORT.getPort());
        portsBinding.bind(exposedPort, new com.github.dockerjava.api.model.Ports.Binding(null, port.toString()));
        return portsBinding;
    }

}
