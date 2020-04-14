package com.rmurugaian.spring.activemq.config;

import com.rmurugaian.spring.activemq.domain.Message;
import com.rmurugaian.spring.activemq.service.EventService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsContext {

    private final EventService eventService;

    public JmsContext(final EventService eventService) {
        this.eventService = eventService;
    }

    @JmsListener(
        destination = "events",
        containerFactory = "jmsListenerContainerFactory"
    )
    public void eventsListener(final Message message) {
        eventService.events(message);
    }

    /*
     * Normally we don't need two factories like jmsListenerContainerFactory, topicListenerFactory,
     * But some cases if we want to create two separate connections specific to queues and topics
     * just follow the below pattern.
     * */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
        @Value("${spring.activemq.broker-url}") final String activeMQUrl,
        final DefaultJmsListenerContainerFactoryConfigurer configurer) {

        //Avoid using CachingConnectionFactory if we have failover activemq configuration
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(new ActiveMQConnectionFactory(activeMQUrl));
        connectionFactory.setReconnectOnException(true);
        connectionFactory.setClientId("consumerOne");

        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);

        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(
        @Value("${spring.activemq.broker-url}") final String activeMQUrl,
        final DefaultJmsListenerContainerFactoryConfigurer configurer) {

        /*
         * Spring provides an implementation of the ConnectionFactory interface, SingleConnectionFactory,
         * that returns the same Connection on all createConnection() calls and ignores calls to close().
         * This is useful for testing and standalone environments so that the same connection can be used for multiple
         * JmsTemplate calls that may span any number of transactions. SingleConnectionFactory takes a reference
         * to a standard ConnectionFactory that would typically come from JNDI.
         * */
        final SingleConnectionFactory connectionFactory = new SingleConnectionFactory();
        connectionFactory.setTargetConnectionFactory(new ActiveMQConnectionFactory(activeMQUrl));
        connectionFactory.setReconnectOnException(true);
        connectionFactory.setClientId("consumerTwo");

        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);

        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(true);

        return factory;
    }

    // Serialize message content to json using TextMessage
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


