package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringKafkaIntegrationApplication {

    private final SimpleProducer simpleProducer;

    public SpringKafkaIntegrationApplication(final SimpleProducer simpleProducer) {
        this.simpleProducer = simpleProducer;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaIntegrationApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        simpleProducer.sendMessage("Hello Hai");
    }
}
