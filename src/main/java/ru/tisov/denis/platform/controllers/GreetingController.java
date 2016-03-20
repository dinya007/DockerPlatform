package ru.tisov.denis.platform.controllers;

import com.github.dockerjava.api.DockerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tisov.denis.platform.Greeting;
import ru.tisov.denis.platform.configuration.DockerConfig;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    private DockerConfig dockerConfig;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/*")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/info")
    public String getInfo() {
        DockerClient dockerClient = dockerConfig.getDockerClient();
        StringBuilder result = new StringBuilder();
        dockerClient.listContainersCmd().exec().forEach(container -> result.append(container.getId()));
        return result.toString();
    }
}