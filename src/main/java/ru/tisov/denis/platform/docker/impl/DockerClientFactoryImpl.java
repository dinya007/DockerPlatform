package ru.tisov.denis.platform.docker.impl;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.services.HostService;

import java.util.Map;
import java.util.Optional;

@Component
@PropertySource("docker.properties")
public class DockerClientFactoryImpl implements DockerClientFactory {

    @Autowired
    private HostService hostService;

    @Value("${registry.url}")
    private String registryUrl;

    private Map<String, DockerClient> hosts = Maps.newHashMap();

    public DockerClient getDockerClient(String hostName) {

        Optional<String> optionalHost = hosts.keySet().stream().filter(host -> host.equals(host)).findFirst();
        if (optionalHost.isPresent()) {
            return hosts.get(optionalHost.get());
        }

        Host host = hostService.getByName(hostName);
        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(host.getUrl())
                .withDockerCertPath(host.getHostPath())
                .withRegistryUrl(registryUrl)
                .build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

        hosts.put(hostName, dockerClient);
        return dockerClient;
    }


}
