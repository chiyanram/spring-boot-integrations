package com.rmurugaian.spring.activemq;

import com.google.common.collect.Maps;
import com.rmurugaian.spring.activemq.domain.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class SpringActivemqConsumerTwoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringActivemqConsumerTwoApplication.class, args);
    }

    @Bean
    public Map<String, Message> dataStore() {
        return Maps.newConcurrentMap();
    }

}
