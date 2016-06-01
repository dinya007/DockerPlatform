package ru.tisov.denis.platform.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.domain.docker.Container;
import ru.tisov.denis.platform.service.LogService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LogServiceImpl implements LogService {

    private final Cache<Container, List<String>> logs;
    private final ContainerDao containerDao;

    @Autowired
    public LogServiceImpl(ContainerDao containerDao) {
        this.containerDao = containerDao;
        logs = CacheBuilder.newBuilder().
                expireAfterAccess(10, TimeUnit.MINUTES).build();
    }

    @Override
    public List<String> getLogs(Container container) {
        containerDao.loadLogs(container);
        return logs.getIfPresent(container);
    }

    @Override
    public List<String> addLogs(Container container, List<String> newLogs) {
        logs.put(container, newLogs);
        return newLogs;
    }

    @Override
    public void loadLogs(Container container) {
        containerDao.loadLogs(container);
    }

}
