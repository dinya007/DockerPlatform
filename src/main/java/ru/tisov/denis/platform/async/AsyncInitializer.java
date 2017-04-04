package ru.tisov.denis.platform.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class AsyncInitializer {

    private final LogConsumer logConsumer;

    @Autowired
    public AsyncInitializer(LogConsumer logConsumer) {
        this.logConsumer = logConsumer;
    }

    @PostConstruct
    private void init() {
        logConsumer.consumeLogs();
    }
}
