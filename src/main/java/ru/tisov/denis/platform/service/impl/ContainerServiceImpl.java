package ru.tisov.denis.platform.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.ContainerService;

@Service
public class ContainerServiceImpl implements ContainerService {

    private static final Logger logger = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final ContainerDao containerDao;

    @Autowired
    public ContainerServiceImpl(ContainerDao containerDao) {
        this.containerDao = containerDao;
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