package ru.tisov.denis.platform.service.impl;

import org.junit.Test;
import org.mockito.Mockito;
import ru.tisov.denis.platform.da.JVMDao;
import ru.tisov.denis.platform.service.JVMService;

public class DefaultJVMServiceTest {

    private final String imageName = "image";
    private final String imageTag = "1.0";
    private final String fullImageName = imageName + ":" + imageTag;

    @Test
    public void getWithImageTag() throws Exception {
        JVMDao jvmDao = Mockito.mock(JVMDao.class);
        JVMService jvmService = new DefaultJVMService(jvmDao);

        jvmService.get(0L, 0L, fullImageName);

        Mockito.verify(jvmDao, Mockito.times(1)).get(0L, 0L, imageName);
    }

    @Test
    public void getWithoutImageTag() throws Exception {
        JVMDao jvmDao = Mockito.mock(JVMDao.class);
        JVMService jvmService = new DefaultJVMService(jvmDao);

        jvmService.get(0L, 0L, imageName);

        Mockito.verify(jvmDao, Mockito.times(1)).get(0L, 0L, imageName);
    }

}