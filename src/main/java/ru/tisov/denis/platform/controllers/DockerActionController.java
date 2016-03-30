package ru.tisov.denis.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tisov.denis.platform.controllers.dto.ContainerCreationDto;
import ru.tisov.denis.platform.controllers.dto.ContainerActionDto;
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
    public void startContainer(@RequestBody ContainerCreationDto containerCreation) {
        String hostName = containerCreation.getHostName();
        String imageName = containerCreation.getImageName();
        String imageTag = containerCreation.getImageTag();
        dockerManager.startContainer(hostName, imageName + ":" + imageTag);
    }

    @RequestMapping(value = "/stopContainer", method = RequestMethod.POST)
    public void stopContainer(@RequestBody ContainerActionDto containerActionDto) {
        String hostName = containerActionDto.getHostName();
        String containerId = containerActionDto.getContainerId();
        dockerManager.stopContainer(hostName, containerId);
    }

    @RequestMapping(value = "/removeContainer", method = RequestMethod.DELETE)
    public void removeContainer(@RequestBody ContainerActionDto containerActionDto) {
        String hostName = containerActionDto.getHostName();
        String containerId = containerActionDto.getContainerId();
        dockerManager.removeContainer(hostName, containerId);
    }

}
