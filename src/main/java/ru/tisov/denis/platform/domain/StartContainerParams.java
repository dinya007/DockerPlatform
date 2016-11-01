package ru.tisov.denis.platform.domain;

import com.github.dockerjava.api.model.Ports;

public class StartContainerParams {

    private final String imageName;
    private final String appName;
    private Ports portsBinding;
    private String networkName;

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

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkName() {
        return networkName;
    }
}
