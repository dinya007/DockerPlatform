package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

public interface DockerService {

    List<Image> getRegistryImages();

    List<Container> getRunningContainers();

    List<Container> getRunningContainersWithoutRegistry();

    List<Container> getAllContainers();

    List<Container> getStoppedContainers();

}
