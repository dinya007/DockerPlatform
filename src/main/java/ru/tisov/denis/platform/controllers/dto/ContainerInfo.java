package ru.tisov.denis.platform.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContainerInfo {

    private String hostName;
    private String containerId;
    private String imageName;
    private String appName;
    private Integer port;

    public ContainerInfo() {
    }

    public ContainerInfo(String hostName, String containerId) {
        this.hostName = hostName;
        this.containerId = containerId;
    }

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerInfo that = (ContainerInfo) o;

        if (hostName != null ? !hostName.equals(that.hostName) : that.hostName != null) return false;
        return containerId != null ? containerId.equals(that.containerId) : that.containerId == null;

    }

    @Override
    public int hashCode() {
        int result = hostName != null ? hostName.hashCode() : 0;
        result = 31 * result + (containerId != null ? containerId.hashCode() : 0);
        return result;
    }
}
