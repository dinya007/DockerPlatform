package ru.tisov.denis.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.JVMDao;
import ru.tisov.denis.platform.service.JVMService;

import java.util.List;

@Service
public class DefaultJVMService implements JVMService {

    private final JVMDao defaultJVMDao;

    @Autowired
    public DefaultJVMService(JVMDao defaultJVMDao) {
        this.defaultJVMDao = defaultJVMDao;
    }

    @Override
    public List<String> get(Long environmentId, Long hostId, String imageName) {
        //TODO Remove it
        if (imageName.contains(":")) {
            imageName = imageName.split(":")[0];
        }
        return defaultJVMDao.get(environmentId, hostId, imageName);
    }

}
