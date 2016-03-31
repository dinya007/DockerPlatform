package ru.tisov.denis.platform.manager.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.async.callbacks.StartAfterPullImageCallback;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.manager.DockerManager;
import ru.tisov.denis.platform.services.HostService;

@Component
public class DockerManagerImpl implements DockerManager {

    private static final Logger logger = LoggerFactory.getLogger(DockerManagerImpl.class);

    private final DockerClientFactory dockerClientFactory;
    private final HostService hostService;

    @Autowired
    public DockerManagerImpl(DockerClientFactory dockerClientFactory, HostService hostService) {
        this.dockerClientFactory = dockerClientFactory;
        this.hostService = hostService;
    }

    @Override
    public void createAndStartContainer(String hostName, String imageName) {
        createContainer(hostName, imageName, true);

    }

    @Override
    public void createContainer(String hostName, String imageName) {
        createContainer(hostName, imageName, false);
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
        stopContainer(hostName, containerId);
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.removeContainerCmd(containerId).exec();
    }

    @Override
    public void restartContainer(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.restartContainerCmd(containerId).exec();
    }

    private void createContainer(String hostName, String imageName, boolean startAfterCreate) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        Host currentHost = hostService.getByName(hostName);

        ExposedPort exposedPort = ExposedPort.tcp(8080);
        Ports portsBinding = new Ports();
        portsBinding.bind(exposedPort, Ports.binding(8081));

        String registryIp = dockerClient.authConfig().getRegistryAddress().split("//")[1].split(":")[0];
        if (registryIp.equals(currentHost.getUrl())) registryIp = "127.0.0.1";

        String fullImageName = registryIp + ":5000" + "/" + imageName;

        try {
            dockerClient.listImagesCmd().exec().forEach(image -> dockerClient.removeImageCmd(image.getId()).withForce(true).exec());
        } catch (ConflictException ex) {
            logger.info("Unable to delete image", ex);
        } finally {
            dockerClient.pullImageCmd(fullImageName).exec(new StartAfterPullImageCallback(fullImageName, dockerClient, portsBinding, startAfterCreate));
        }
    }

}