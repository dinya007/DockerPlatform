package ru.tisov.denis.platform.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.DockerDao;
import ru.tisov.denis.platform.da.EnvironmentDao;
import ru.tisov.denis.platform.docker.DockerDaoFactory;
import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.domain.Network;
import ru.tisov.denis.platform.service.EnvironmentService;
import ru.tisov.denis.platform.service.HostService;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentDao environmentDAO;
    private final DockerDaoFactory dockerDaoFactory;

    @Autowired
    public EnvironmentServiceImpl(EnvironmentDao environmentDAO, DockerDaoFactory dockerDaoFactory, HostService hostService) {
        this.environmentDAO = environmentDAO;
        this.dockerDaoFactory = dockerDaoFactory;
    }

    @Override
    public List<Environment> getAll() {
        return Lists.newArrayList(environmentDAO.findAll());
    }

    @Override
    public Environment getById(Long id) {
        return environmentDAO.getById(id);
    }

    @Override
    public Environment create(Environment environment) {
        if (environment.getCreatedDate() == null) environment.setCreatedDate(LocalDateTime.now());
        environment.setModifiedDate(LocalDateTime.now());


        DockerDao dockerDao = dockerDaoFactory.getDockerDao(environment.getHosts().get(0).getName());
        dockerDao.createNetwork(environment.getName());

        environment.setNetworks(singletonList(Network.builder()
            .name(environment.getName())
            .createdDate(LocalDateTime.now())
            .modifiedDate(LocalDateTime.now())
            .build()));
        return environmentDAO.save(environment);
    }

    @Override
    public Environment addHost(Long environmentId, Host host) {
        Environment environment = getById(environmentId);
        environment.getHosts().add(host);
        return environmentDAO.save(environment);
    }
}
