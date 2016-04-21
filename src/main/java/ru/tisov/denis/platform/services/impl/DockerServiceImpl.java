package ru.tisov.denis.platform.services.impl;

import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.services.DockerService;

import java.util.List;
import java.util.stream.Collectors;

public class DockerServiceImpl implements DockerService {

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
        return getRunningContainers().stream().filter(container -> !container.getPort().equals("5000")).collect(Collectors.toList());
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
