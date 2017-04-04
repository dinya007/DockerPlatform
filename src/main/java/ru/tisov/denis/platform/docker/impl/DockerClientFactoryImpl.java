package ru.tisov.denis.platform.docker.impl;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.service.HostService;

import java.util.Map;
import java.util.Optional;

@Component
@PropertySource("classpath:docker.properties")
public class DockerClientFactoryImpl implements DockerClientFactory {

    private final HostService hostService;

    @Value("${registry.url}")
    private String registryUrl;

    private Map<String, DockerClient> hosts = Maps.newHashMap();

    @Autowired
    public DockerClientFactoryImpl(HostService hostService) {
        this.hostService = hostService;
    }

    public DockerClient getDockerClient(String hostName) {

        Optional<String> optionalHost = hosts.keySet().stream().filter(host -> host.equals(hostName)).findFirst();
        if (optionalHost.isPresent()) {
            return hosts.get(optionalHost.get());
        }

        Host host = hostService.getByName(hostName);
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://" + host.getUrl() + ":2376")
                .withDockerCertPath(host.getHostPath())
                .withApiVersion("1.12")
                .withRegistryUrl(registryUrl)
                .build();

        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .build();

        hosts.put(hostName, dockerClient);
        return dockerClient;
    }


}
