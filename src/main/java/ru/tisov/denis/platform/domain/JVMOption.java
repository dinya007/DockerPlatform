package ru.tisov.denis.platform.domain;

public class JVMOption {

    private final Environment environment;
    private final Host host;
    private final String image;
    private final String options;

    public JVMOption(Environment environment, Host host, String image, String options) {
        this.environment = environment;
        this.host = host;
        this.image = image;
        this.options = options;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Host getHost() {
        return host;
    }

    public String getImage() {
        return image;
    }

    public String getOptions() {
        return options;
    }
}
