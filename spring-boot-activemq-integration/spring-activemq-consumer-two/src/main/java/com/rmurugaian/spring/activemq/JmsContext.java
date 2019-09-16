package com.rmurugaian.spring.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;

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
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactoryQueue() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        final SingleConnectionFactory  connectionFactory = new SingleConnectionFactory();
        connectionFactory.setTargetConnectionFactory(activeMQConnectionFactory());
        connectionFactory.setReconnectOnException(true);
        connectionFactory.setClientId("consumerTwo");
        return connectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(){
        final CachingConnectionFactory  connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(activeMQConnectionFactoryQueue());
        connectionFactory.setReconnectOnException(true);
        connectionFactory.setClientId("consumerOne");

        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(
        final ConnectionFactory connectionFactory,
        final DefaultJmsListenerContainerFactoryConfigurer configurer) {

        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);

        factory.setSubscriptionDurable(true);

        return factory;
    }

    @JmsListener(
        destination = "event-topic", containerFactory = "topicListenerFactory"
    )
    public void eventsListenerTopic(final String message) {
        eventService.events(message);
    }

}


