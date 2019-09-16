package com.rmurugaian.spring.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
class JmsContext {

    @Autowired
    private EventService eventService;

    @JmsListener(
        destination = "events"
    )
    public void eventsListener(final String message) {
        eventService.events(message);
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(
        final ConnectionFactory connectionFactory,
        final DefaultJmsListenerContainerFactoryConfigurer configurer) {

        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    @JmsListener(
        destination = "event-topic", containerFactory = "topicListenerFactory"
    )
    public void eventsListenerTopic(final String message) {
        eventService.events(message);
    }

}


