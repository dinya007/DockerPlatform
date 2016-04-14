package ru.tisov.denis.platform.async.callbacks;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

public class LogCallback implements ResultCallback<Frame> {

    private static final Logger logger = LoggerFactory.getLogger(LogCallback.class);


    @Override
    public void onStart(Closeable closeable) {
        logger.debug("Start logging download.");
    }

    @Override
    public void onNext(Frame object) {
        logger.info(object.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("Error has occurred while reading logs", throwable);
    }

    @Override
    public void onComplete() {
        logger.debug("Logs reading completed");
    }

    @Override
    public void close() throws IOException {
        logger.debug("Logs reading closed");
    }
}