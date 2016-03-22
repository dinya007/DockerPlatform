package ru.tisov.denis.platform.docker;

import ru.tisov.denis.platform.services.DockerService;

public interface DockerServiceFactory {

    DockerService getDockerService(String hostName);

}
