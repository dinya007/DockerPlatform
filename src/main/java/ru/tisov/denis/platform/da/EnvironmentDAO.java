package ru.tisov.denis.platform.da;

import org.springframework.data.repository.CrudRepository;
import ru.tisov.denis.platform.domain.Environment;

import java.util.List;

public interface EnvironmentDAO extends CrudRepository<Environment, Long> {

//    List<Environment> getAll();
    Environment getById(Long id);

}
