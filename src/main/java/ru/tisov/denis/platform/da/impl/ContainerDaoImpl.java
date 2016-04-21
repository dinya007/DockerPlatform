package ru.tisov.denis.platform.da.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.async.callbacks.LogCallback;
import ru.tisov.denis.platform.async.callbacks.StartAfterPullImageCallback;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.docker.Log;
import ru.tisov.denis.platform.services.HostService;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

@Component
public class ContainerDaoImpl implements ContainerDao {


    private static final Logger logger = LoggerFactory.getLogger(ContainerDaoImpl.class);

    @Resource(name="logQueue")
    private BlockingQueue<Log> logQueue;
    private final DockerClientFactory dockerClientFactory;
    private final HostService hostService;

    @Autowired
    public ContainerDaoImpl(DockerClientFactory dockerClientFactory, HostService hostService) {
        this.dockerClientFactory = dockerClientFactory;
        this.hostService = hostService;
    }

    @Override
    public void startContainer(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.startContainerCmd(containerId).exec();
    }

    @Override
    public void stopContainer(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        try {
            dockerClient.stopContainerCmd(containerId).exec();
        } catch (NotModifiedException e) {
            logger.info("Container already stopped.", e);
        }
    }

    @Override
    public void removeContainer(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.removeContainerCmd(containerId).exec();
    }

    @Override
    public void restartContainer(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.restartContainerCmd(containerId).exec();
    }

    @Override
    public void loadLogs(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.logContainerCmd(containerId).withStdErr(true).withStdOut(true).withTail(200).exec(new LogCallback(logQueue, hostName, containerId));
    }

    @Override
    public void createContainer(String hostName, String imageName, String appName, Integer port, boolean startAfterCreate) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        Host currentHost = hostService.getByName(hostName);

        Ports portsBinding = null;
        if (port != null) {
            ExposedPort exposedPort = ExposedPort.tcp(8080);
            portsBinding = new Ports();
            portsBinding.bind(exposedPort, Ports.binding(port));
        }

        String registryIp = dockerClient.authConfig().getRegistryAddress().split("//")[1].split(":")[0];
        if (registryIp.equals(currentHost.getUrl())) registryIp = "127.0.0.1";

        String fullImageName = registryIp + ":5000" + "/" + imageName;

        try {
            dockerClient.listImagesCmd().exec().forEach(image -> dockerClient.removeImageCmd(image.getId()).withForce(true).exec());
        } catch (ConflictException ex) {
            logger.info("Unable to delete image", ex);
        } finally {
            dockerClient.pullImageCmd(fullImageName).exec(new StartAfterPullImageCallback(fullImageName, appName, dockerClient, portsBinding, startAfterCreate));
        }
    }

}
