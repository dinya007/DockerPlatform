package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.docker.Container;

import java.util.List;

public interface LogService {

    List<String> getLogs(Container container);

    List<String> addLogs(Container container, List<String> logs);

    void loadLogs(Container container);
}
