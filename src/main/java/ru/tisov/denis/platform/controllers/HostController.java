package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.services.DockerService;
import ru.tisov.denis.platform.services.HostService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/hosts")
public class HostController {

    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;

    @Autowired
    public HostController(HostService hostService, DockerServiceFactory dockerServiceFactory) {
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
    }

    @RequestMapping("/{id}")
    public String getAllEnvironments(@PathVariable Long id) {
        return "host";
    }

    @ResponseBody
    @RequestMapping("/all")
    public List<Host> getAllHosts() {
        return hostService.getAll();
    }

    @ResponseBody
    @RequestMapping("/{id}/hostInfo")
    public Host getHostInfo(@PathVariable Long id) {
        return hostService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/repositoryImages")
    public List<Image> getRepositoryImages() {
        Host host = hostService.getRegistryHost();
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());

        List<Image> registryImages;
        try {
            registryImages = dockerService.getRegistryImages();
        } catch (ResourceAccessException | HttpClientErrorException ex) {
            registryImages=  Collections.emptyList();
        }
        return registryImages;
    }

    @ResponseBody
    @RequestMapping("/{id}/runningContainers")
    public List<Container> getRunningContainers(@PathVariable Long id) {
        Host host = hostService.getById(id);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());
        return dockerService.getRunningContainersWithoutRegistry();
    }

    @ResponseBody
    @RequestMapping("/{id}/stoppedContainers")
    public List<Container> getStoppedContainers(@PathVariable Long id) {
        Host host = hostService.getById(id);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());
        return dockerService.getStoppedContainers();
    }

}
