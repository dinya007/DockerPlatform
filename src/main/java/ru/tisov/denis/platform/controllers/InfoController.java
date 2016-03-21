package ru.tisov.denis.platform.controllers;

import com.github.dockerjava.api.DockerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tisov.denis.platform.configuration.DockerConfig;

@RestController
public class InfoController {

    @Autowired
    private DockerConfig dockerConfig;

    @RequestMapping("/info")
    public String getInfo() {
        DockerClient dockerClient = dockerConfig.getDockerClient();
        StringBuilder result = new StringBuilder();
        dockerClient.listContainersCmd().exec().forEach(container -> result.append(container.getId()));
        return result.toString();
    }
}