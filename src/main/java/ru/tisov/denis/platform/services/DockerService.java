package ru.tisov.denis.platform.services;

import ru.tisov.denis.platform.domain.Image;

import java.util.List;

public interface DockerService {

    List<Image> getRegistryImages();

}
