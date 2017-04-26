package ru.tisov.denis.platform.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.ApplicationDao;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final ContainerDao containerDao;
    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;
    private final PropertyService propertyService;
    private final JVMService jvmService;
    private final ApplicationDao applicationDao;

    @Autowired
    public ContainerServiceImpl(ContainerDao containerDao, HostService hostService, DockerServiceFactory dockerServiceFactory, PropertyService propertyService, JVMService jvmService, ApplicationDao applicationDao, ApplicationDao applicationDao1) {
        this.containerDao = containerDao;
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
        this.propertyService = propertyService;
        this.jvmService = jvmService;
        this.applicationDao = applicationDao1;
    }

    @Override
    public void createAndStart(Container container) {
        Host host = hostService.getByName(container.getHostName());
        containerDao.create(container, true, propertyService.get(container),
                jvmService.get(container.getEnvironmentId(), host.getId(), container.getImageName()));
    }

    @Override
    public void create(Container container) {
        Host host = hostService.getByName(container.getHostName());
        containerDao.create(container, false, propertyService.get(container),
                jvmService.get(container.getEnvironmentId(), host.getId(), container.getImageName()));
    }

    @Override
    public List<Container> getRunning(Long hostId, Long environmentId) {
        Host host = hostService.getById(hostId);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());
        return dockerService.getRunningContainersWithoutRegistry().stream()
                .filter(filterContainers(environmentId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Container> getStopped(Long hostId, Long environmentId) {
        Host host = hostService.getById(hostId);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());
        return dockerService.getStoppedContainers().stream()
            .filter(filterContainers(environmentId))
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

    private Predicate<Container> filterContainers(Long environmentId) {
        return container -> {
            Long containersEnvironmentId = applicationDao.getEnvironmentId(container.getId());
            if (environmentId.equals(containersEnvironmentId)) {
                container.setEnvironmentId(containersEnvironmentId);
                return true;
            }
            return false;
        };
    }

}