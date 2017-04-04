package ru.tisov.denis.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.ImagePropertyDao;
import ru.tisov.denis.platform.domain.Property;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.PropertyService;

import java.util.List;

/**
 * @author dinyat
 *         03/04/2017
 */
@Service
public class DefaultPropertyService implements PropertyService {

    private final ImagePropertyDao imagePropertyDao;

    @Autowired
    public DefaultPropertyService(ImagePropertyDao imagePropertyDao) {
        this.imagePropertyDao = imagePropertyDao;
    }


    @Override
    public List<Property> get(Container container) {
        //TODO Remove it
        String imageName = container.getImageName();
        if (imageName.contains(":")) {
            imageName = imageName.split(":")[0];
        }
        return imagePropertyDao.get(imageName);
    }

}
