package ru.tisov.denis.platform.manager;

public interface DockerManager {

    void createAndStartContainer(String hostName, String imageName);

    void createContainer(String hostName, String imageName);

    void startContainer(String hostName, String containerId);

    void stopContainer(String hostName, String containerId);

    void removeContainer(String hostName, String containerId);

    void restartContainer(String hostName, String containerId);
}
