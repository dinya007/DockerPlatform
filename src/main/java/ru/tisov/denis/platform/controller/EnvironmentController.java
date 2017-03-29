package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.service.EnvironmentService;

import java.util.List;

@Controller
public class EnvironmentController {

    private final EnvironmentService environmentService;

    @Autowired
    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @RequestMapping(value = {"/*"})
    public String getEnvironmentPage() {
        return "app/index.html";
    }

    @RequestMapping(value = "/environment/all")
    @ResponseBody
    public List<Environment> getAllEnvironment() {
        return environmentService.getAll();
    }

    @RequestMapping(value = "/environment/{environmentId}")
    @ResponseBody
    public Environment getAllEnvironment(@PathVariable Long environmentId) {
        return environmentService.getById(environmentId);
    }

}
