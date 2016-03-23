package ru.tisov.denis.platform.services.impl;

import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.services.DockerService;

import java.util.List;

public class DockerServiceImpl implements DockerService {

    private final DockerDao dockerDao;

    public DockerServiceImpl(DockerDao dockerDao) {
        this.dockerDao = dockerDao;
    }

    public List<Image> getRegistryImages() {
        return dockerDao.getRegistryImages();

    }
}
