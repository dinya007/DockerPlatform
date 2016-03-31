package ru.tisov.denis.platform.da.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ContainerPort;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.domain.docker.ImageTags;
import ru.tisov.denis.platform.domain.docker.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DockerDaoImpl implements DockerDao {

    private final Logger logger = LoggerFactory.getLogger(DockerDaoImpl.class);

    private final DockerClient dockerClient;
    private final RestTemplate restTemplate = new RestTemplate();

    public DockerDaoImpl(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @Override
    public List<Image> getRegistryImages() {
        List<Image> result = Lists.newArrayList();

        Repository repository = restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + "/v2/_catalog", Repository.class);

        List<String> imageNames = repository.getImageNames();

        for (String imageName : imageNames) {
            ImageTags tags = restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + "/v2/" + imageName + "/tags/list", ImageTags.class);
            result.add(new Image(imageName, tags.getTags()));
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

    private class ContainerMapper implements Function<com.github.dockerjava.api.model.Container, Container> {

        private List<Container> runningContainers;

        public ContainerMapper(){

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
            result.setPort((ports.length > 0) ? String.valueOf(ports[0].getPublicPort()) : "");
            result.setNetworks(Lists.newArrayList(container.getNetworkSettings().getNetworks().keySet()));
            result.setStatus(container.getStatus());
            result.setRunning(runningContainers == null || runningContainers.contains(result));
            result.setBaseImage(container.getImage());
            return result;
        }
    }

}
