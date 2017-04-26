package ru.tisov.denis.platform.da;

import java.util.Map;

/**
 * @author dinyat
 *         26/04/2017
 */
public interface ApplicationDao {

    Long getEnvironmentId(String containerId);

    void saveEnvironmentId(String containerId, Long environmentId);

    Map<String, Long> getAll();

}
