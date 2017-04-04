package ru.tisov.denis.platform.da;

import ru.tisov.denis.platform.domain.Property;
import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

public interface ContainerDao {

    void start(Container container);

    void stop(Container container);

    void remove(Container container);

    void restart(Container container);

    void loadLogs(Container container);

    void create(Container container, boolean startAfterCreate, List<Property> properties, List<String> jvmArgs);
}
