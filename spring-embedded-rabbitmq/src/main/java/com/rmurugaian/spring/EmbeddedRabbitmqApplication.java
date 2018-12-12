package com.rmurugaian.spring;


import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class EmbeddedRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedRabbitmqApplication.class, args);
    }
}


@Component
class EmbeddedRabbit {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedRabbit.class.getName());

    EmbeddedRabbitMq rabbitMq;

    @PostConstruct
    public void init() {
        final EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().build();
        rabbitMq = new EmbeddedRabbitMq(config);
        rabbitMq.start();

        LOGGER.debug("Embedded Rabbitmq Server Started {} ", rabbitMq.toString());
    }

    @PreDestroy
    public void destroy() {
        rabbitMq.stop();
    }


}