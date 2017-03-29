package ru.tisov.denis.platform.async.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.tisov.denis.platform.async.LogConsumer;
import ru.tisov.denis.platform.domain.docker.Log;
import ru.tisov.denis.platform.service.LogService;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

@Component
public class LogConsumerImpl implements LogConsumer {

    @Resource(name = "logQueue")
    private BlockingQueue<Log> logQueue;
    private final LogService logService;

    @Autowired
    public LogConsumerImpl(LogService logService) {
        this.logService = logService;
    }

    @Override
    @Async
    public void consumeLogs() {
        while (true) {
            try {
                Log log = logQueue.take();
                logService.addLogs(log.getContainer(), log.getLogs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
