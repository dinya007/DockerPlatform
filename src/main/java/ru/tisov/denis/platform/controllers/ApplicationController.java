package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tisov.denis.platform.domain.Application;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.services.ApplicationService;
import ru.tisov.denis.platform.services.HostService;

import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final HostService hostService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, HostService hostService) {
        this.applicationService = applicationService;
        this.hostService = hostService;
    }

    @RequestMapping("/*")
    public String getAllEnvironments() {
        return "apps";
    }

    @ResponseBody
    @RequestMapping("/getAll/{hostName}")
    public List<Application> getAll(@PathVariable String hostName) {
        Host host = hostService.getByName(hostName);
        return applicationService.getRunningApplications(host);
    }

}
