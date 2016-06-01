package ru.tisov.denis.platform.enums;

public enum Port {

    REGISTRY_PORT(5000),
    DEFAULT_PORT(8080);

    private final int port;

    Port(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
