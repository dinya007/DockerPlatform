package ru.tisov.denis.platform.domain;

import com.github.dockerjava.api.model.Ports;

public class StartContainerParams {

    private final String imageName;
    private final String appName;
    private Ports portsBinding;

    public StartContainerParams(String imageName, String appName) {
        this.imageName = imageName;
        this.appName = appName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getAppName() {
        return appName;
    }

    public Ports getPortsBinding() {
        return portsBinding;
    }

    public void setPortsBinding(Ports portsBinding) {
        this.portsBinding = portsBinding;
    }
}
