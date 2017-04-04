package ru.tisov.denis.platform.service.impl;

import org.junit.Test;
import ru.tisov.denis.platform.da.ImagePropertyDao;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.PropertyService;

import static org.mockito.Mockito.*;

/**
 * @author dinyat
 *         04/04/2017
 */
public class DefaultPropertyServiceTest {

    private final String imageName = "image";
    private final String imageTag = "1.0";
    private final String fullImageName = imageName + ":" + imageTag;
    private final ImagePropertyDao imagePropertyDao = mock(ImagePropertyDao.class);
    private final PropertyService propertyService = new DefaultPropertyService(imagePropertyDao);

    @Test
    public void testGetWithTag() throws Exception {
        Container container = new Container();
        container.setImageName(fullImageName);

        propertyService.get(container);

        verify(imagePropertyDao, times(1)).get(imageName);
    }

    @Test
    public void testGetWithoutTag() throws Exception {
        Container container = new Container();
        container.setImageName(imageName);

        propertyService.get(container);

        verify(imagePropertyDao, times(1)).get(imageName);
    }

}