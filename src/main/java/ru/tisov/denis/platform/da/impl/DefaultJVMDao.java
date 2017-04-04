package ru.tisov.denis.platform.da.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.da.JVMDao;

import java.util.List;

@Component
public class DefaultJVMDao implements JVMDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultJVMDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> get(Long environmentId, Long hostId, String imageName) {
        return jdbcTemplate.queryForList("SELECT value FROM jvm_args WHERE environment_id = ? and host_id = ? and image_name = ?", new Object[]{environmentId, hostId, imageName}, String.class);
    }
}
