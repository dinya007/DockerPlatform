package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tisov.denis.platform.controllers.dto.ContainerCreationDto;
import ru.tisov.denis.platform.controllers.dto.ContainerActionDto;
import ru.tisov.denis.platform.controllers.dto.ContainerStartDto;
import ru.tisov.denis.platform.manager.DockerManager;

@Controller
@RequestMapping("/action")
public class DockerActionController {

    private final DockerManager dockerManager;

    @Autowired
    public DockerActionController(DockerManager dockerManager) {
        this.dockerManager = dockerManager;
    }

    @RequestMapping(value = "/createContainer", method = RequestMethod.PUT)
    public void createContainer(@RequestBody ContainerCreationDto containerCreationDto) {
        String hostName = containerCreationDto.getHostName();
        String imageName = containerCreationDto.getImageName();
        dockerManager.createContainer(hostName, imageName);
    }

    @RequestMapping(value = "/startContainer", method = RequestMethod.POST)
    public void startContainer(@RequestBody ContainerStartDto containerStartDto) {
        String hostName = containerStartDto.getHostName();
        String containerId = containerStartDto.getContainerId();
        dockerManager.startContainer(hostName, containerId);
    }

    @RequestMapping(value = "/createStartContainer", method = RequestMethod.PUT)
    public void createAndStartContainer(@RequestBody ContainerCreationDto containerCreationDto) {
        String hostName = containerCreationDto.getHostName();
        String imageName = containerCreationDto.getImageName();
        dockerManager.createAndStartContainer(hostName, imageName);
    }

    @RequestMapping(value = "/stopContainer", method = RequestMethod.POST)
    public void stopContainer(@RequestBody ContainerActionDto containerActionDto) {
        String hostName = containerActionDto.getHostName();
        String containerId = containerActionDto.getContainerId();
        dockerManager.stopContainer(hostName, containerId);
    }

    @RequestMapping(value = "/removeContainer/{hostName}/{containerId}", method = RequestMethod.DELETE)
    public void removeContainer(@PathVariable String hostName, @PathVariable String containerId) {
        dockerManager.removeContainer(hostName, containerId);
    }

    @RequestMapping(value = "/restartContainer", method = RequestMethod.POST)
    public void restartContainer(@RequestBody ContainerActionDto containerActionDto) {
        String hostName = containerActionDto.getHostName();
        String containerId = containerActionDto.getContainerId();
        dockerManager.restartContainer(hostName, containerId);
    }

}
