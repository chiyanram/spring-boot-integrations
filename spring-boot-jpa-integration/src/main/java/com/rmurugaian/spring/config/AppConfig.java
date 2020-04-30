package com.rmurugaian.spring.config;

import com.rmurugaian.spring.sequence.SequenceEventListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class AppConfig {

    @Bean
    public PreInsertEventListener dijtaPreInsertIdListener(final EntityManagerFactory entityManagerFactory) {
        final SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        final ServiceRegistryImplementor serviceRegistry = sessionFactory.getServiceRegistry();
        final EventListenerRegistry registry = serviceRegistry.getService(EventListenerRegistry.class);
        final SequenceEventListener sequenceEventListener = new SequenceEventListener();
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(sequenceEventListener);
        return sequenceEventListener;
    }
}
