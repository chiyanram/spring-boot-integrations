package com.rmurugaian.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SomeUpdater implements ApplicationListener<ContextStartedEvent> {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SomeUpdater(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;

    }

    @Override
    public void onApplicationEvent(final ContextStartedEvent event) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            //some logic
            applicationEventPublisher.publishEvent(new PurposeEvent(this, 5));
        });
    }

    @Override
    public String toString() {
        return "SomeUpdater Class {}";
    }
}

