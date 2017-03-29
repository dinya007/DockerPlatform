package ru.tisov.denis.platform.enums;

public enum Ports {

    REGISTRY_PORT(5000),
    DEFAULT_PORT(8080);

    private final int port;

    Ports(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
