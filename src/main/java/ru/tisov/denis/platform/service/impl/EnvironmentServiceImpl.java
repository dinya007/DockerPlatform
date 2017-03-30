package ru.tisov.denis.platform.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.EnvironmentDao;
import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.service.EnvironmentService;

import java.util.List;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    private final EnvironmentDao environmentDAO;

    @Autowired
    public EnvironmentServiceImpl(EnvironmentDao environmentDAO) {
        this.environmentDAO = environmentDAO;
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
    public Environment save(Environment environment) {
        return environmentDAO.save(environment);
    }
}
