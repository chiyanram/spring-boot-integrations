package com.rmurugaian.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SimpleConsumer {

    @KafkaListener(id = "simple-consumer", topics = "simple-message")
    public void consumeMessage(final String message) {
        log.info("Got message: {}", message);
    }

    @KafkaListener(id = "simple-consumer", topics = "simple-message.DLT")
    public void consumeMessageError(final String message) {
        log.info("Got message: {}", message);
    }
}