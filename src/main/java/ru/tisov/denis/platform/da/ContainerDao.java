package ru.tisov.denis.platform.da;

public interface ContainerDao {

    void createContainer(String hostName, String imageName, boolean startAfterCreating);

    void startContainer(String hostName, String containerId);

    void stopContainer(String hostName, String containerId);

    void removeContainer(String hostName, String containerId);

    void restartContainer(String hostName, String containerId);

    void loadLogs(String hostName, String containerId);

}
