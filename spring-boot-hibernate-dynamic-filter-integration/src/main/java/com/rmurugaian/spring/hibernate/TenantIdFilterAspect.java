package com.rmurugaian.spring.hibernate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Aspect
@Transactional
public class TenantIdFilterAspect {

    private final EntityManager entityManager;

    public TenantIdFilterAspect(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Around("execution(* com.rmurugaian.spring.hibernate.PersonRepository+.*(..))")
    public Object inWebLayer(final ProceedingJoinPoint joinPoint) throws Throwable {
        if (entityManager.isOpen()) {
            final Session session = entityManager.unwrap(Session.class);
            final Long id = 1L;
            session.enableFilter("mFilter").setParameter("tenantId", id);
        }
        return joinPoint.proceed();
    }
}
