package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.DockerService;
import ru.tisov.denis.platform.service.EnvironmentService;
import ru.tisov.denis.platform.service.HostService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/hosts")
public class HostController {

    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;
    private final EnvironmentService environmentService;

    @Autowired
    public HostController(HostService hostService, DockerServiceFactory dockerServiceFactory, EnvironmentService environmentService) {
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
        this.environmentService = environmentService;
    }

    @RequestMapping("/{id}")
    public String getAllEnvironments(@PathVariable Long id) {
        return "apps";
    }

    @ResponseBody
    @RequestMapping("/all")
    public List<Host> getAllHosts() {
        return hostService.getAll();
    }

    @ResponseBody
    @RequestMapping("/all/{environmentId}")
    public List<Host> getHostByEnvironmentId(@PathVariable Long environmentId) {
        return environmentService.getById(environmentId).getHosts();
    }

    @ResponseBody
    @RequestMapping("/{id}/hostInfo")
    public Host getHostInfo(@PathVariable Long id) {
        return hostService.getById(id);
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
