package com.rmurugaian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringKafkaIntegrationApplication {
    
    public static void main(final String[] args) {
        SpringApplication.run(SpringKafkaIntegrationApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(final SimpleProducer simpleProducer) {
        simpleProducer.sendMessage("Hello Hai");
    }
}
