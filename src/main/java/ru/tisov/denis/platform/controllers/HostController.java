package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tisov.denis.platform.services.HostService;

@Controller
public class HostController {

    private final HostService hostService;

    @Autowired
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @RequestMapping("/hosts/{id}")
    public String getAllEnvironments(@PathVariable Long id, Model model) {
        model.addAttribute("host", hostService.getById(id));
        return "host";
    }

}
