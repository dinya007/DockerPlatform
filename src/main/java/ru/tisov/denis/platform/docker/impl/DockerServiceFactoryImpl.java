package ru.tisov.denis.platform.docker.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.docker.DockerDaoFactory;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.service.DockerService;
import ru.tisov.denis.platform.service.impl.DockerServiceImpl;

@Component
public class DockerServiceFactoryImpl implements DockerServiceFactory {

    @Autowired
    private DockerDaoFactory dockerDaoFactory;

    @Override
    public DockerService getDockerService(String hostName) {
        return new DockerServiceImpl(dockerDaoFactory.getDockerDao(hostName));
    }
}
