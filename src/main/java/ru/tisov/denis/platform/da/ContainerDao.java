package ru.tisov.denis.platform.da;

import ru.tisov.denis.platform.domain.docker.Container;

public interface ContainerDao {

    void start(Container container);

    void stop(Container container);

    void remove(Container container);

    void restart(Container container);

    void create(Container container, boolean startAfterCreate);

    void loadLogs(Container container);
}
