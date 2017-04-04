package ru.tisov.denis.platform.da.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.da.ImagePropertyDao;
import ru.tisov.denis.platform.domain.Property;

import java.util.List;

/**
 * @author dinyat
 *         04/04/2017
 */
@Component
public class DefaultImagePropertyDao implements ImagePropertyDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultImagePropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> get(String imageName) {
        return jdbcTemplate.query("SELECT name, value FROM image_properties WHERE image_name = ?", new Object[]{imageName}, new BeanPropertyRowMapper(Property.class));
    }
}
