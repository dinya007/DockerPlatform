package ru.tisov.denis.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.ContainerService;
import ru.tisov.denis.platform.service.LogService;

@Controller
@RequestMapping("/action")
public class DockerActionController {

    private final ContainerService containerService;
    private final LogService logService;

    @Autowired
    public DockerActionController(ContainerService containerService, LogService logService) {
        this.containerService = containerService;
        this.logService = logService;
    }

    @RequestMapping(value = "/createContainer", method = RequestMethod.PUT)
    public void createContainer(@RequestBody Container container) {
        containerService.create(container);
    }

    @RequestMapping(value = "/startContainer", method = RequestMethod.POST)
    public void startContainer(@RequestBody Container container) {
        containerService.start(container);
    }

    @RequestMapping(value = "/createStartContainer", method = RequestMethod.PUT)
    public void createAndStartContainer(@RequestBody Container container) {
        containerService.createAndStart(container);
    }

    @RequestMapping(value = "/stopContainer", method = RequestMethod.POST)
    public void stopContainer(@RequestBody Container container) {
        containerService.stop(container);
    }

    @RequestMapping(value = "/removeContainer/{hostName}/{containerId}", method = RequestMethod.DELETE)
    public void removeContainer(@PathVariable String hostName, @PathVariable String containerId) {
        Container container = new Container();
        container.setHostName(hostName);
        container.setId(containerId);
        containerService.remove(container);
    }

    @RequestMapping(value = "/restartContainer", method = RequestMethod.POST)
    public void restartContainer(@RequestBody Container container) {
        containerService.restart(container);
    }

    @RequestMapping(value = "/logs", method = RequestMethod.POST)
    public void loadLogs(@RequestBody Container container) {
        logService.loadLogs(container);
    }


}
