package ru.tisov.denis.platform.configuration;

import com.github.dockerjava.api.DockerClient;

public interface DockerConfig {
    DockerClient getDockerClient();
}
