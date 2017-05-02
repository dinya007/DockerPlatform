package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.ContainerService;
import ru.tisov.denis.platform.service.EnvironmentService;
import ru.tisov.denis.platform.service.HostService;

import java.util.List;

@Controller
@RequestMapping("/hosts")
public class HostController {

    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;
    private final EnvironmentService environmentService;
    private final ContainerService containerService;

    @Autowired
    public HostController(HostService hostService, DockerServiceFactory dockerServiceFactory, EnvironmentService environmentService, ContainerService containerService) {
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
        this.environmentService = environmentService;
        this.containerService = containerService;
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
    @RequestMapping("/{hostId}/runningContainers/{environmentId}")
    public List<Container> getRunningContainers(@PathVariable Long hostId, @PathVariable Long environmentId) {
        return containerService.getRunning(hostId, environmentId);
    }

    @ResponseBody
    @RequestMapping("/{hostId}/stoppedContainers/{environmentId}")
    public List<Container> getStoppedContainers(@PathVariable Long hostId, @PathVariable Long environmentId) {
        return containerService.getStopped(hostId, environmentId);
    }

}
