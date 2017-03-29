package ru.tisov.denis.platform.docker;

import ru.tisov.denis.platform.service.DockerService;

public interface DockerServiceFactory {

    DockerService getDockerService(String hostName);

}
