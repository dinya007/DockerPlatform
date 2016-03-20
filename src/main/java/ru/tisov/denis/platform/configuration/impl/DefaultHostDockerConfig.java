package ru.tisov.denis.platform.configuration.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.configuration.DockerConfig;

import javax.inject.Inject;

@Component
@PropertySource("docker.properties")
public class DefaultHostDockerConfig implements DockerConfig {

//    @Value("${docker.host.url}")
//    private String DOCKER_HOST_URL;
//    @Value("${docker.machine.path}")
//    private String dockerMachinePath;

    private final DockerClient dockerClient;

    private String hostName = "default";
    private String hostUrl = "tcp://192.168.99.100:2376";

    @Inject
    public DefaultHostDockerConfig(@Value("${host.url}") String dockerHostUrl, @Value("${machine.path}") String dockerMachinePath) {
        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHostUrl)
                .withDockerCertPath(dockerMachinePath + hostName).build();
        dockerClient = DockerClientBuilder.getInstance(config).build();
    }

    @Override
    public DockerClient getDockerClient() {
        return dockerClient;
    }
}
