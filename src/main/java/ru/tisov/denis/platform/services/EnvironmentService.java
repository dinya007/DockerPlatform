package ru.tisov.denis.platform.services;

import ru.tisov.denis.platform.domain.Environment;

import java.util.List;

public interface EnvironmentService {

    List<Environment> getAll();
    Environment getById(Long id);

}
