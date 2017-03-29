package ru.tisov.denis.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.ApplicationDao;
import ru.tisov.denis.platform.domain.Application;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.service.ApplicationService;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    public List<Application> getRunningApplications(Host host) {
        return applicationDao.getAllByHostId(host.getId());
    }
}
