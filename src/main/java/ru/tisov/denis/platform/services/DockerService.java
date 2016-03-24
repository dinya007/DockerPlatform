package ru.tisov.denis.platform.services;

import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

public interface DockerService {

    List<Image> getRegistryImages();

    List<Container> getRunningContainers();

}
