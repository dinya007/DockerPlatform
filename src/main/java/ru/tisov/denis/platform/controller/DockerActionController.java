package ru.tisov.denis.platform.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/container/create", method = RequestMethod.PUT)
    @ResponseBody
    public Result createContainer(@RequestBody Container container, @RequestParam("start") boolean start) {
        if (start) {
            containerService.createAndStart(container);
        } else {
            containerService.create(container);
        }
        return new Result(container.getId());
    }

    @RequestMapping(value = "/container/start", method = RequestMethod.POST)
    @ResponseBody
    public Result startContainer(@RequestBody Container container) {
        containerService.start(container);
        return new Result(container.getId());
    }

    @RequestMapping(value = "/container/create/start", method = RequestMethod.PUT)
    @ResponseBody
    public Result createAndStartContainer(@RequestBody Container container) {
        containerService.createAndStart(container);
        return new Result(container.getId());
    }

    @RequestMapping(value = "/container/stop", method = RequestMethod.POST)
    @ResponseBody
    public Result stopContainer(@RequestBody Container container) {
        containerService.stop(container);
        return new Result(container.getId());
    }

    @RequestMapping(value = "/container/delete/{hostName}/{containerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result removeContainer(@PathVariable String hostName, @PathVariable String containerId) {
        Container container = new Container();
        container.setHostName(hostName);
        container.setId(containerId);
        containerService.remove(container);
        return new Result(container.getId());
    }

    @RequestMapping(value = "/container/restart", method = RequestMethod.POST)
    @ResponseBody
    public Result restartContainer(@RequestBody Container container) {
        containerService.restart(container);
        return new Result(container.getId());
    }

    @RequestMapping(value = "/logs", method = RequestMethod.POST)
    @ResponseBody
    public Result loadLogs(@RequestBody Container container) {
        logService.loadLogs(container);
        return new Result(container.getId());
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Result {
        private final String id;

        private Result(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

}
