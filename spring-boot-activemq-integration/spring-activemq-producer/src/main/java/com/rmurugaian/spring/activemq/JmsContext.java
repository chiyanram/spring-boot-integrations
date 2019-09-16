package com.rmurugaian.spring.activemq;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsContext {

    @Bean
    public ActiveMQTopic activeMQTopic() {

        return new ActiveMQTopic("event-topic");
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
    public JmsListenerContainerFactory<?> queueListenerFactory(
        final ConnectionFactory connectionFactory,
        final DefaultJmsListenerContainerFactoryConfigurer configurer) {

        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
