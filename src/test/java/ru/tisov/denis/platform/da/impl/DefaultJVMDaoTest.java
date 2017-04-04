package ru.tisov.denis.platform.da.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tisov.denis.platform.TestApplication;

import java.util.List;

public class DefaultJVMDaoTest extends TestApplication {

    @Autowired
    private DefaultJVMDao defaultJVMDao;

    @Test
    public void get() throws Exception {
        List<String> jvmArgs = defaultJVMDao.get(0L, 0L, "test_container_1");

        Assert.assertEquals("-Xmx512m", jvmArgs.get(0));
        Assert.assertEquals("-Xms256m", jvmArgs.get(1));
    }

}