package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tisov.denis.platform.docker.DockerServiceFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.services.DockerService;
import ru.tisov.denis.platform.services.HostService;

@Controller
public class HostController {

    private final HostService hostService;
    private final DockerServiceFactory dockerServiceFactory;

    @Autowired
    public HostController(HostService hostService, DockerServiceFactory dockerServiceFactory) {
        this.hostService = hostService;
        this.dockerServiceFactory = dockerServiceFactory;
    }

    @RequestMapping("/hosts/{id}")
    public String getAllEnvironments(@PathVariable Long id, Model model) {
        Host host = hostService.getById(id);
        DockerService dockerService = dockerServiceFactory.getDockerService(host.getName());

        model.addAttribute("host", host);
        model.addAttribute("images", dockerService.getRegistryImages());
        return "host";
    }

}
