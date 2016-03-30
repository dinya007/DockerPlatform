package ru.tisov.denis.platform.manager;

public interface DockerManager {

    void startContainer(String hostName, String imageName);

    void stopContainer(String hostName, String containerId);

    void removeContainer(String hostName, String containerId);

}
