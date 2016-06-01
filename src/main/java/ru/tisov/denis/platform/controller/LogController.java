package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.LogService;

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
        Container container = new Container();
        container.setId(containerId);
        container.setHostName(hostName);
        return logService.getLogs(container);
    }

}
