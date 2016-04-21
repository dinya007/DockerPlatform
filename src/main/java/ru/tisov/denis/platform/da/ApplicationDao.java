package ru.tisov.denis.platform.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tisov.denis.platform.domain.Application;

import java.util.List;

@Repository
public interface ApplicationDao extends CrudRepository<Application, Long> {

    List<Application> getAllByHostId(Long hostId);

}
