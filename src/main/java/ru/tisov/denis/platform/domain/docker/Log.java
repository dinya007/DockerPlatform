package ru.tisov.denis.platform.domain.docker;

import java.util.List;

public class Log {

    private Container container;
    private List<String> logs;

    public Log(Container container, List<String> logs) {
        this.container = container;
        this.logs = logs;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}
