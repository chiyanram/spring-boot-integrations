package com.rmurugaian.spring.activemq.config;

import com.rmurugaian.spring.activemq.domain.Message;
import com.rmurugaian.spring.activemq.service.EventService;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsContext {

    private final EventService eventService;

    public JmsContext(final EventService eventService) {
        this.eventService = eventService;
    }

    @JmsListener(
        destination = "events"
    )
    public void eventsListener(final Message message) {
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

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        final MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setTargetType(MessageType.TEXT);
        mappingJackson2MessageConverter.setTypeIdPropertyName("_type");
        return mappingJackson2MessageConverter;
    }

    @JmsListener(
        destination = "event-topic", containerFactory = "topicListenerFactory"
    )
    public void eventsListenerTopic(final Message message) {
        eventService.events(message);
    }

}


