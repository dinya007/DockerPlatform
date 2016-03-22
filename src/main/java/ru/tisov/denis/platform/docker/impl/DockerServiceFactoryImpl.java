package ru.tisov.denis.platform.docker.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.services.DockerService;
import ru.tisov.denis.platform.services.impl.DockerServiceImpl;

@Component
public class DockerServiceFactoryImpl implements DockerServiceFactory {

    @Autowired
    private DockerClientFactory dockerClientFactory;

    @Override
    public DockerService getDockerService(String hostName) {
        return new DockerServiceImpl(dockerClientFactory.getDockerClient(hostName));
    }
}
