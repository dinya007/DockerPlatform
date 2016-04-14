package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tisov.denis.platform.controllers.dto.ContainerInfo;
import ru.tisov.denis.platform.services.ContainerService;

@Controller
@RequestMapping("/action")
public class DockerActionController {

    private final ContainerService containerService;

    @Autowired
    public DockerActionController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @RequestMapping(value = "/createContainer", method = RequestMethod.PUT)
    public void createContainer(@RequestBody ContainerInfo containerInfo) {
        containerService.createContainer(containerInfo);
    }

    @RequestMapping(value = "/startContainer", method = RequestMethod.POST)
    public void startContainer(@RequestBody ContainerInfo containerInfo) {
        containerService.startContainer(containerInfo);
    }

    @RequestMapping(value = "/createStartContainer", method = RequestMethod.PUT)
    public void createAndStartContainer(@RequestBody ContainerInfo containerInfo) {
        containerService.createAndStartContainer(containerInfo);
    }

    @RequestMapping(value = "/stopContainer", method = RequestMethod.POST)
    public void stopContainer(@RequestBody ContainerInfo containerInfo) {
        containerService.stopContainer(containerInfo);
    }

    @RequestMapping(value = "/removeContainer/{hostName}/{containerId}", method = RequestMethod.DELETE)
    public void removeContainer(@PathVariable String hostName, @PathVariable String containerId) {
        ContainerInfo containerInfo = new ContainerInfo();
        containerInfo.setHostName(hostName);
        containerInfo.setContainerId(containerId);
        containerService.removeContainer(containerInfo);
    }

    @RequestMapping(value = "/restartContainer", method = RequestMethod.POST)
    public void restartContainer(@RequestBody ContainerInfo containerInfo) {
        containerService.restartContainer(containerInfo);
    }

    @RequestMapping(value = "/logs", method = RequestMethod.POST)
    public void loadLogs(@RequestBody ContainerInfo containerInfo) {
        containerService.loadLogs(containerInfo);
    }


}
