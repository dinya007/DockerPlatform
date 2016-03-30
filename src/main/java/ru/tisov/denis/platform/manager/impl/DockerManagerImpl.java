package ru.tisov.denis.platform.manager.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.manager.DockerManager;

@Component
public class DockerManagerImpl implements DockerManager {

    private final DockerClientFactory dockerClientFactory;

    @Autowired
    public DockerManagerImpl(DockerClientFactory dockerClientFactory) {
        this.dockerClientFactory = dockerClientFactory;
    }

    @Override
    public void startContainer(String hostName, String imageName) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);

        ExposedPort exposedPort = ExposedPort.tcp(8080);
        Ports portsBinding = new Ports();
        portsBinding.bind(exposedPort, Ports.binding(8081));

        String registryIp = dockerClient.authConfig().getRegistryAddress().split("//")[1];

        //TODO check case if we trying to launch on registry machine
        //TODO NoSuchImage

        CreateContainerResponse createContainerResponse = dockerClient.
                createContainerCmd(registryIp + "/" + imageName).
                withPortBindings(portsBinding).exec();

        //TODO check container creation mistakes

        dockerClient.startContainerCmd(createContainerResponse.getId()).exec();
    }

    @Override
    public void stopContainer(String hostName, String containerId) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);
        dockerClient.stopContainerCmd(containerId).exec();
    }

    @Override
    public void removeContainer(String hostName, String containerId) {
        stopContainer(hostName, containerId);

        DockerClient dockerClient = dockerClientFactory.getDockerClient(hostName);

        dockerClient.removeContainerCmd(containerId).exec();
    }
}
