package ru.tisov.denis.platform.da.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.model.ContainerPort;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.domain.docker.ImageTags;
import ru.tisov.denis.platform.domain.docker.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DockerDaoImpl implements DockerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DockerDaoImpl.class);

    private final DockerClient dockerClient;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Host host;

    public DockerDaoImpl(DockerClient dockerClient, Host host) {
        this.dockerClient = dockerClient;
        this.host = host;
    }

    @Override
    public List<Image> getRegistryImages() {
        List<Image> result = Lists.newArrayList();

        Repository repository = restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + "/_catalog", Repository.class);

        List<String> imageNames = repository.getImageNames();

        for (String imageName : imageNames) {
            ImageTags imageTags = restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + imageName + "/tags/list", ImageTags.class);
            if (imageTags.getTags() != null && !imageTags.getTags().isEmpty())
                result.add(new Image(imageName, imageTags.getTags()));
        }

        return result;
    }

    @Override
    public List<Container> getAllContainers() {
        List<com.github.dockerjava.api.model.Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        return containers.stream().map(new ContainerMapper(getRunningContainers())).collect(Collectors.toList());
    }

    @Override
    public List<Container> getRunningContainers() {
        List<com.github.dockerjava.api.model.Container> containers = dockerClient.listContainersCmd().exec();
        return containers.stream().map(new ContainerMapper()).collect(Collectors.toList());
    }

    @Override
    public List<Container> getStoppedContainers() {
        List<Container> allContainers = getAllContainers();
        return allContainers.stream().filter(container -> !container.isRunning()).collect(Collectors.toList());
    }

    @Override
    public String createNetwork(String name) {
        if (!host.isSwarmMaster()) {
            throw new IllegalStateException("Only swarm master host can create network");
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("attachable", null);
        CreateNetworkResponse response = dockerClient.createNetworkCmd()
            .withDriver("overlay")
            .withName(name)
            .withOptions(params).exec();
        return name;
    }

    private class ContainerMapper implements Function<com.github.dockerjava.api.model.Container, Container> {

        private List<Container> runningContainers;

        public ContainerMapper() {

        }

        public ContainerMapper(List<Container> runningContainers) {
            this.runningContainers = runningContainers;
        }

        @Override
        public Container apply(com.github.dockerjava.api.model.Container container) {
            ContainerPort[] ports = container.getPorts();

            Container result = new Container();
            result.setId(container.getId());
            result.setName(container.getNames()[0]);
            result.setPort((ports.length > 0) ? ports[0].getPublicPort() : null);
            result.setNetworks(Lists.newArrayList(container.getNetworkSettings().getNetworks().keySet()));
            result.setStatus(container.getStatus());
            result.setRunning(runningContainers == null || runningContainers.contains(result));
            result.setImageName(container.getImage());
            result.setHostName(host.getName());
            return result;
        }
    }

}
