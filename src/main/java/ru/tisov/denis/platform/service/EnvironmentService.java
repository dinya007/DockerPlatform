package ru.tisov.denis.platform.service;

import ru.tisov.denis.platform.domain.Environment;

import java.util.List;

public interface EnvironmentService {

    List<Environment> getAll();

    Environment getById(Long id);

    Environment create(Environment environment);

}
