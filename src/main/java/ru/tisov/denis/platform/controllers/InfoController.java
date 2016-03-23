package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.services.DockerService;

import java.util.List;

@RestController
public class InfoController {

    @Autowired
    private DockerServiceFactory dockerServiceFactory;

    @RequestMapping("/info")
    public List<Image> getInfo() {
        DockerService dockerService = dockerServiceFactory.getDockerService("default");
        return dockerService.getRegistryImages();
    }
}