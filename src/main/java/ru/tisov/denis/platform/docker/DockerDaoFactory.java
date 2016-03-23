package ru.tisov.denis.platform.docker;

import ru.tisov.denis.platform.da.DockerDao;

public interface DockerDaoFactory {

    DockerDao getDockerDao(String hostName);

}
