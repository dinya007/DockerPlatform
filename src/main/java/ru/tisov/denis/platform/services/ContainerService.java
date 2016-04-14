package ru.tisov.denis.platform.services;

import ru.tisov.denis.platform.controllers.dto.ContainerInfo;

public interface ContainerService {

    void loadLogs(ContainerInfo containerInfo);

    void restartContainer(ContainerInfo containerInfo);

    void startContainer(ContainerInfo containerInfo);

    void stopContainer(ContainerInfo containerInfo);

    void removeContainer(ContainerInfo containerInfo);

    void createAndStartContainer(ContainerInfo containerInfo);

    void createContainer(ContainerInfo containerInfo);
}
