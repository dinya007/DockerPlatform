package ru.tisov.denis.platform.da.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tisov.denis.platform.Application;
import ru.tisov.denis.platform.da.ImagePropertyDao;

/**
 * @author dinyat
 *         04/04/2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DefaultImagePropertyDaoTest {

    @Autowired
    private ImagePropertyDao imagePropertyDao;

    @Test
    public void testGet() throws Exception {
        System.out.println(imagePropertyDao.get("docker_1"));
    }


}
