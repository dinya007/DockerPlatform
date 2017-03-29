package ru.tisov.denis.platform.service.impl;

import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.DockerService;

import java.util.List;
import java.util.stream.Collectors;

public class DockerServiceImpl implements DockerService {

    private final Integer registryPort = 5000;
    private final DockerDao dockerDao;

    public DockerServiceImpl(DockerDao dockerDao) {
        this.dockerDao = dockerDao;
    }

    @Override
    public List<Image> getRegistryImages() {
        return dockerDao.getRegistryImages();

    }

    @Override
    public List<Container> getRunningContainers() {
        return dockerDao.getRunningContainers();
    }

    @Override
    public List<Container> getRunningContainersWithoutRegistry() {
        return getRunningContainers().stream().filter(container -> {
            Integer port = container.getPort();
            return port == null || !port.equals(registryPort);
        }).collect(Collectors.toList());
    }

    @Override
    public List<Container> getAllContainers() {
        return dockerDao.getAllContainers();
    }

    @Override
    public List<Container> getStoppedContainers() {
        return dockerDao.getStoppedContainers();
    }

}
