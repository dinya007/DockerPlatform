package ru.tisov.denis.platform.da;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tisov.denis.platform.domain.Network;

/**
 * @author dinyat
 *         26/04/2017
 */
@Repository
public interface NetworkDao extends CrudRepository<Network, Long> {
}
