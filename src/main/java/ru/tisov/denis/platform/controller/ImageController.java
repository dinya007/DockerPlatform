package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.service.DockerService;
import ru.tisov.denis.platform.service.HostService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/images")
public class ImageController {

    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;

    @Autowired
    public ImageController(HostService hostService, DockerServiceFactory dockerServiceFactory) {
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
    }

    @ResponseBody
    @RequestMapping("")
    public List<Image> getRepositoryImages() {
        Host host = hostService.getRegistryHost();
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());

        List<Image> registryImages;
        try {
            registryImages = dockerService.getRegistryImages();
        } catch (ResourceAccessException | HttpClientErrorException ex) {
            registryImages = Collections.emptyList();
        }
        return registryImages;
    }

}
