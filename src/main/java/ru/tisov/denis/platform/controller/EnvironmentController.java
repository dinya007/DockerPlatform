package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


//    @RequestMapping(value = {"/env/", "/*"})
    public String getAllEnvironments(Model model) {
        model.addAttribute("environments", environmentService.getAll());
        return "environments";
    }

    @RequestMapping(value = {"/env/", "/*"})
    public String getEnvironmentPage() {
        return "app/index.html";
    }

    @RequestMapping("/env/{id}")
    public String getEnvironment(@PathVariable Long id, Model model) {
        model.addAttribute("hosts", environmentService.getById(id).getHosts());
        model.addAttribute("selectedEnvironment", id);
        return "environment";
    }

    @RequestMapping("/env/all")
    public List<Environment> getAllEnvironment(@PathVariable Long id, Model model) {
        return environmentService.getAll();
    }

}
