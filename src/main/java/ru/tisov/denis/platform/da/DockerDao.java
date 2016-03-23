package ru.tisov.denis.platform.da;

import ru.tisov.denis.platform.domain.Image;

import java.util.List;

public interface DockerDao {

    List<Image> getRegistryImages();

}
