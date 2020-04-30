package com.rmurugaian.spring.sequence;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class MetadataExtractorIntegrator implements Integrator {

    public static Metadata METADATA = null;

    @Override
    public void integrate(
        final Metadata metadata,
        final SessionFactoryImplementor sessionFactory,
        final SessionFactoryServiceRegistry serviceRegistry) {

        MetadataExtractorIntegrator.METADATA = metadata;
    }

    @Override
    public void disintegrate(
        final SessionFactoryImplementor sessionFactory,
        final SessionFactoryServiceRegistry serviceRegistry) {

    }
}
