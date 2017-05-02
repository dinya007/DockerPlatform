package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.EnvironmentService;

import java.util.List;

@Controller
@RequestMapping("/environment")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @Autowired
    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @RequestMapping(value = "/all")
    @ResponseBody
    public List<Environment> getAllEnvironment() {
        return environmentService.getAll();
    }

    @RequestMapping(value = "/{environmentId}", method = RequestMethod.GET)
    @ResponseBody
    public Environment getAllEnvironment(@PathVariable Long environmentId) {
        return environmentService.getById(environmentId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Environment createEnvironment(@RequestBody Environment environment) {
        return environmentService.create(environment);
    }

    @ResponseBody
    @RequestMapping(value = "/{environmentId}", method = RequestMethod.POST)
    public Environment addHost(@PathVariable Long environmentId, @RequestBody Host host) {
        return environmentService.addHost(environmentId, host);
    }

}
