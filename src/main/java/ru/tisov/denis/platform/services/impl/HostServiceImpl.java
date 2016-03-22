package ru.tisov.denis.platform.services.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.HostDao;
import ru.tisov.denis.platform.domain.Host;
import ru.tisov.denis.platform.services.HostService;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostDao hostDao;

    @Autowired
    public HostServiceImpl(HostDao hostDao) {
        this.hostDao = hostDao;
    }

    @Override
    public Host getById(Long id) {
        return hostDao.findById(id);
    }

    @Override
    public Host getByName(String hostName) {
        return hostDao.getByName(hostName);
    }

    @Override
    public List<Host> getAll() {
        return Lists.newArrayList(hostDao.findAll());
    }

    @Override
    public List<Host> getAll(List<Long> ids) {
        return Lists.newArrayList(hostDao.findAll(ids));
    }

    @Override
    public Host getRegistryHost() {
        return hostDao.getHostByIsRegistry(true);
    }
}
