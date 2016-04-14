package ru.tisov.denis.platform.services.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tisov.denis.platform.controllers.dto.ContainerInfo;
import ru.tisov.denis.platform.da.ContainerDao;
import ru.tisov.denis.platform.services.LogService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LogServiceImpl implements LogService {

    private final Cache<ContainerInfo, List<String>> logs;
    private final ContainerDao containerDao;

    @Autowired
    public LogServiceImpl(ContainerDao containerDao) {
        this.containerDao = containerDao;
        logs = CacheBuilder.newBuilder().
                expireAfterAccess(10, TimeUnit.MINUTES).build();
    }

    @Override
    public List<String> getLogs(ContainerInfo containerInfo) {
        containerDao.loadLogs(containerInfo.getHostName(), containerInfo.getContainerId());
        return logs.getIfPresent(containerInfo);
    }

    @Override
    public List<String> addLogs(ContainerInfo containerInfo, List<String> newLogs) {
        logs.put(containerInfo, newLogs);
        return newLogs;
    }

}
