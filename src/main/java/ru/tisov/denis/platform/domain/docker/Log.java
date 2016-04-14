package ru.tisov.denis.platform.domain.docker;

import ru.tisov.denis.platform.controllers.dto.ContainerInfo;

import java.util.List;

public class Log {

    private ContainerInfo containerInfo;
    private List<String> logs;

    public Log(ContainerInfo containerInfo, List<String> logs) {
        this.containerInfo = containerInfo;
        this.logs = logs;
    }

    public ContainerInfo getContainerInfo() {
        return containerInfo;
    }

    public void setContainerInfo(ContainerInfo containerInfo) {
        this.containerInfo = containerInfo;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}
