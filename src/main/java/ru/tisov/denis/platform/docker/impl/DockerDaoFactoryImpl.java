package ru.tisov.denis.platform.docker.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.da.HostDao;
import ru.tisov.denis.platform.da.impl.DockerDaoImpl;
import ru.tisov.denis.platform.docker.DockerClientFactory;
import ru.tisov.denis.platform.docker.DockerDaoFactory;

@Component
public class DockerDaoFactoryImpl implements DockerDaoFactory {

    @Autowired
    private DockerClientFactory dockerClientFactory;
    @Autowired
    private HostDao hostDao;

    @Override
    public DockerDao getDockerDao(String hostName) {
        return new DockerDaoImpl(dockerClientFactory.getDockerClient(hostName), hostDao.getByName(hostName ));
    }
}
