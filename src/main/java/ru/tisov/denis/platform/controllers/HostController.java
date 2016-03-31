package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.services.DockerService;
import ru.tisov.denis.platform.services.HostService;

import java.util.Collections;

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
    public String getAllEnvironments(@PathVariable Long id, Model model) {
        Host host = hostService.getById(id);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());

        model.addAttribute("host", host);
        model.addAttribute("runningContainers", dockerService.getRunningContainers());
        model.addAttribute("stoppedContainers", dockerService.getStoppedContainers());

        try {
            model.addAttribute("images", dockerService.getRegistryImages());
        } catch (ResourceAccessException ex) {
            model.addAttribute("images", Collections.emptyList());
        } catch (HttpClientErrorException ex) {
            model.addAttribute("images", Collections.emptyList());
        }

        return "host";
    }

}
