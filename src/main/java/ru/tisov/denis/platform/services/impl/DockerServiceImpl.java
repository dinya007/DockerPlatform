package ru.tisov.denis.platform.services.impl;

import com.github.dockerjava.api.DockerClient;
import org.springframework.web.client.RestTemplate;
import ru.tisov.denis.platform.services.DockerService;

public class DockerServiceImpl implements DockerService {

    private final DockerClient dockerClient;
    private RestTemplate restTemplate = new RestTemplate();

    public DockerServiceImpl(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public String getRegistryImages() {
        return restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + "/v2/_catalog", String.class);
    }
}
