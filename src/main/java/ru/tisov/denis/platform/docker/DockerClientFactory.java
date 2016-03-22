package ru.tisov.denis.platform.docker;

import com.github.dockerjava.api.DockerClient;

public interface DockerClientFactory {

    DockerClient getDockerClient(String hostName);

}
