package ru.tisov.denis.platform.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tisov.denis.platform.domain.Environment;

@Repository
public interface EnvironmentDao extends CrudRepository<Environment, Long> {

    Environment getById(Long id);

}
