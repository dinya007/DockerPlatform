package ru.tisov.denis.platform.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tisov.denis.platform.domain.Host;

@Repository
public interface HostDao extends CrudRepository<Host, Long> {

    Host findById(Long id);

}
