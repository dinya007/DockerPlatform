package ru.tisov.denis.platform.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.controllers.dto.ContainerInfo;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.services.ContainerService;

@Service
public class ContainerServiceImpl implements ContainerService {

    private static final Logger logger = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final ContainerDao containerDao;

    @Autowired
    public ContainerServiceImpl(ContainerDao containerDao) {
        this.containerDao = containerDao;
    }

    @Override
    public void createAndStartContainer(ContainerInfo containerInfo) {
        containerDao.createContainer(containerInfo.getHostName(), containerInfo.getImageName(), containerInfo.getAppName(), containerInfo.getPort(), true);
    }

    @Override
    public void createContainer(ContainerInfo containerInfo) {
        containerDao.createContainer(containerInfo.getHostName(), containerInfo.getImageName(), containerInfo.getAppName(), containerInfo.getPort(), false);
    }

    @Override
    public void startContainer(ContainerInfo containerInfo) {
        containerDao.startContainer(containerInfo.getHostName(), containerInfo.getContainerId());
    }

    @Override
    public void stopContainer(ContainerInfo containerInfo) {
        containerDao.stopContainer(containerInfo.getHostName(), containerInfo.getContainerId());
    }

    @Override
    public void removeContainer(ContainerInfo containerInfo) {
        String hostName = containerInfo.getHostName();
        String containerId = containerInfo.getContainerId();
        containerDao.stopContainer(hostName, containerId);
        containerDao.removeContainer(hostName, containerId);
    }

    @Override
    public void restartContainer(ContainerInfo containerInfo) {
        containerDao.restartContainer(containerInfo.getHostName(), containerInfo.getContainerId());
    }

    @Override
    public void loadLogs(ContainerInfo containerInfo) {
        containerDao.loadLogs(containerInfo.getHostName(), containerInfo.getContainerId());
    }


}