package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.docker.Container;

public interface ContainerService {

    void restart(Container container);

    void start(Container container);

    void stop(Container container);

    void remove(Container container);

    void createAndStart(Container container);

    void create(Container container);
}
