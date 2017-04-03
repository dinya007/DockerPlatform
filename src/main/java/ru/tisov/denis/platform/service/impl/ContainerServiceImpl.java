package ru.tisov.denis.platform.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.ContainerService;
import ru.tisov.denis.platform.service.DockerService;
import ru.tisov.denis.platform.service.HostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {

    private static final Logger logger = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final ContainerDao containerDao;
    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;

    @Autowired
    public ContainerServiceImpl(ContainerDao containerDao, HostService hostService, DockerServiceFactory dockerServiceFactory) {
        this.containerDao = containerDao;
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
    }

    @Override
    public void createAndStart(Container container) {
        containerDao.create(container, true);
    }

    @Override
    public void create(Container container) {
        containerDao.create(container, false);
    }

    @Override
    public List<Container> getRunning(Long hostId, Long environmentId) {
        Host host = hostService.getById(hostId);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());
        return dockerService.getRunningContainersWithoutRegistry().stream()
//            .filter(container -> container.getEnvironmentId().equals(environmentId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Container> getStopped(Long hostId, Long environmentId) {
        Host host = hostService.getById(hostId);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());
        return dockerService.getStoppedContainers().stream()
//            .filter(container -> container.getEnvironmentId().equals(environmentId))
            .collect(Collectors.toList());
    }

    @Override
    public void start(Container container) {
        containerDao.start(container);
    }

    @Override
    public void stop(Container container) {
        containerDao.stop(container);
    }

    @Override
    public void remove(Container container) {
        containerDao.stop(container);
        containerDao.remove(container);
    }

    @Override
    public void restart(Container container) {
        containerDao.restart(container);
    }


}