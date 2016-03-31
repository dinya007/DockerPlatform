package ru.tisov.denis.platform.controllers.dto;

public class ContainerStartDto {

    private String hostName;
    private String containerId;


    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}
