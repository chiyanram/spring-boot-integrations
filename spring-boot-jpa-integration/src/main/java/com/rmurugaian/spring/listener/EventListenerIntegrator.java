package com.rmurugaian.spring.listener;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import static org.hibernate.event.spi.EventType.PRE_INSERT;

public class EventListenerIntegrator implements Integrator {

    public static final EventListenerIntegrator INSTANCE = new EventListenerIntegrator();

    @Override
    public void integrate(
        final Metadata metadata,
        final SessionFactoryImplementor sessionFactory,
        final SessionFactoryServiceRegistry serviceRegistry) {

        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

        final SequenceEventListener sequenceEventListener =
            new SequenceEventListener(metadata, sessionFactory, serviceRegistry);

        eventListenerRegistry.appendListeners(PRE_INSERT, sequenceEventListener);
        eventListenerRegistry.appendListeners(PRE_INSERT, StatusInsertEventListener.INSTANCE);
        
    }

    @Override
    public void disintegrate(
        final SessionFactoryImplementor sessionFactory,
        final SessionFactoryServiceRegistry serviceRegistry) {

    }
}