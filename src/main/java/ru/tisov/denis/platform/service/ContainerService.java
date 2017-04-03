package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

public interface ContainerService {

    void restart(Container container);

    void start(Container container);

    void stop(Container container);

    void remove(Container container);

    void createAndStart(Container container);

    void create(Container container);

    List<Container> getRunning(Long hostId, Long environmentId);

    List<Container> getStopped(Long hostId, Long environmentId);
}
