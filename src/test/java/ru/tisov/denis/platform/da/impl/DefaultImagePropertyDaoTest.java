package ru.tisov.denis.platform.da.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tisov.denis.platform.TestApplication;
import ru.tisov.denis.platform.da.ImagePropertyDao;
import ru.tisov.denis.platform.domain.Property;

import java.util.List;

/**
 * @author dinyat
 *         04/04/2017
 */
public class DefaultImagePropertyDaoTest extends TestApplication {

    @Autowired
    private ImagePropertyDao imagePropertyDao;

    @Test
    public void testGet() throws Exception {
        List<Property> properties = imagePropertyDao.get("test_container_1");

        Assert.assertEquals("first.property", properties.get(0).getName());
        Assert.assertEquals("firstValue", properties.get(0).getValue());
        Assert.assertEquals("second.property", properties.get(1).getName());
        Assert.assertEquals("secondValue", properties.get(1).getValue());
    }


}
