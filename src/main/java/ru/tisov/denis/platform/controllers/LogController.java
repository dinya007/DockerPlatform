package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tisov.denis.platform.controllers.dto.ContainerInfo;
import ru.tisov.denis.platform.services.LogService;

import java.util.List;

@Controller
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping("/")
    public String getLogsPage() {
        return "logs";
    }

    @ResponseBody
    @RequestMapping(value = "/{hostName}/{containerId}", method = RequestMethod.GET)
    public List<String> getLogs(@PathVariable String hostName, @PathVariable String containerId) {
        ContainerInfo containerInfo = new ContainerInfo(hostName, containerId);
        return logService.getLogs(containerInfo);
    }

}
