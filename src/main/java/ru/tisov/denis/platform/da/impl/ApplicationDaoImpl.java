package ru.tisov.denis.platform.da.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tisov.denis.platform.da.ApplicationDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dinyat
 *         26/04/2017
 */
@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long getEnvironmentId(String containerId) {
        try {
            return jdbcTemplate.queryForObject("SELECT environment_id FROM applications WHERE container_id = ?", Long.class, containerId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("There is no stored environment_id for {}", containerId);
            return null;
        }
    }

    @Override
    public void saveEnvironmentId(String containerId, Long environmentId) {
        jdbcTemplate.update("INSERT INTO applications (container_id, environment_id) VALUES  (?, ?)", containerId, environmentId);
    }

    @Override
    public Map<String, Long> getAll() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT container_id, environment_id FROM applications");
        HashMap<String, Long> values = new HashMap<>();
        for (Map<String, Object> pair : result) {
            values.put(String.valueOf(pair.get("container_id")), Long.valueOf(String.valueOf(pair.get("environment_id"))));
        }
        return values;
    }
}
