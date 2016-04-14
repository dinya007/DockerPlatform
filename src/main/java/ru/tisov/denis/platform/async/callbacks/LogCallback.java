package ru.tisov.denis.platform.async.callbacks;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tisov.denis.platform.controllers.dto.ContainerInfo;
import ru.tisov.denis.platform.domain.docker.Log;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class LogCallback implements ResultCallback<Frame> {

    private static final Logger logger = LoggerFactory.getLogger(LogCallback.class);
    private final BlockingQueue<Log> logQueue;
    private final List<String> logs = new ArrayList<>();
    private final ContainerInfo containerInfo;

    public LogCallback(BlockingQueue<Log> logQueue, String hostName, String containerId) {
        this.logQueue = logQueue;

        containerInfo = new ContainerInfo();
        containerInfo.setHostName(hostName);
        containerInfo.setContainerId(containerId);
    }

    @Override
    public void onStart(Closeable closeable) {
        logger.debug("Start logging download.");
    }

    @Override
    public void onNext(Frame object) {
        logs.add(object.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("Error has occurred while reading logs", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("Logs reading completed");
        try {
            Collections.reverse(logs);
            logQueue.put(new Log(containerInfo, logs));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        logger.debug("Logs reading closed");
    }
}