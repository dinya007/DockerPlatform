package ru.tisov.denis.platform.services;

import ru.tisov.denis.platform.controllers.dto.ContainerInfo;

import java.util.List;

public interface LogService {

    List<String> getLogs(ContainerInfo containerInfo);

    List<String> addLogs(ContainerInfo containerInfo, List<String> logs);

}
