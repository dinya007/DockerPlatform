package ru.tisov.denis.platform.enums;

public enum Hosts {

    LOCAL_HOST("127.0.0.1");

    private final String ip;

    Hosts(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }
}
