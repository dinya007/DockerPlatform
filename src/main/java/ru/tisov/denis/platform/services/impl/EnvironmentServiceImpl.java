package ru.tisov.denis.platform.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.da.EnvironmentDAO;
import ru.tisov.denis.platform.domain.Environment;
import ru.tisov.denis.platform.services.EnvironmentService;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    private EnvironmentDAO environmentDAO;

    @Override
    public List<Environment> getAll() {
        List<Environment> result = new ArrayList<>();
        for (Environment environment: environmentDAO.findAll()) {
            result.add(environment);
        }
        return result;
    }

    @Override
    public Environment getById(Long id) {
        return environmentDAO.getById(id);
    }
}
