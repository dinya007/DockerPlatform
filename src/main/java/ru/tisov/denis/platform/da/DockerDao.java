package ru.tisov.denis.platform.da;

import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

public interface DockerDao {

    List<Image> getRegistryImages();

    List<Container> getAllContainers();

    List<Container> getRunningContainers();

    List<Container> getStoppedContainers();
}