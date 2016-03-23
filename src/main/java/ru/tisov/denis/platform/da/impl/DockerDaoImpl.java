package ru.tisov.denis.platform.da.impl;

import com.github.dockerjava.api.DockerClient;
import com.google.common.collect.Lists;
import org.springframework.web.client.RestTemplate;
import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.domain.Image;
import ru.tisov.denis.platform.domain.docker.ImageTags;
import ru.tisov.denis.platform.domain.docker.Repository;

import java.util.List;

public class DockerDaoImpl implements DockerDao {

    private final DockerClient dockerClient;
    private final RestTemplate restTemplate = new RestTemplate();

    public DockerDaoImpl(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @Override
    public List<Image> getRegistryImages() {
        List<Image> result = Lists.newArrayList();

        Repository repository = restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + "/v2/_catalog", Repository.class);

        List<String> imageNames = repository.getImageNames();

        for (String imageName : imageNames) {
            ImageTags tags = restTemplate.getForObject(dockerClient.authConfig().getRegistryAddress() + "/v2/" + imageName + "/tags/list", ImageTags.class);
            result.add(new Image(imageName, tags.getTags()));
        }

        return result;
    }
}
