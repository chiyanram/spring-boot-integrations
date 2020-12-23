package com.rmurugaian.spring.hibernate.service;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.spring.hibernate.domain.DynamicDataModel;
import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@Log4j2
public class HibernateDynamicDataModelService implements DynamicDataModelService {

    private static final ImmutableSet<String> DEFAULT_KEYS = ImmutableSet.of("$type$", "pkId", "deletedAt");

    private final SessionFactory sessionFactory;
    private final HibernateTransactionManager hibernateTransactionManager;

    public HibernateDynamicDataModelService(
        final SessionFactory sessionFactory,
        final HibernateTransactionManager hibernateTransactionManager) {

        this.hibernateTransactionManager = hibernateTransactionManager;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TransactionStatus beginTransaction(final TransactionDefinition transactionDefinition) {
        return hibernateTransactionManager.getTransaction(transactionDefinition);
    }

    @Override
    public void commit(final TransactionStatus transactionStatus) {
        hibernateTransactionManager.commit(transactionStatus);
    }

    @Override
    public void rollback(final TransactionStatus transactionStatus) {

    }

    @Override
    public DynamicDataModel save(final DynamicDataModel dynamicDataModel) {

        try {
            final Session currentSession = sessionFactory.getCurrentSession();
            final Object model = currentSession
                .save(dynamicDataModel.getEntityName(), dynamicDataModel.toMap());

            log.info("Generated identifier from database {} ", model);

            dynamicDataModel.setPkId((Long) model);

            return dynamicDataModel;

        } catch (final HibernateException e) {
            log.error("Unable to save entity {} in the database with error {} ", dynamicDataModel, e.getMessage());
            throw e;
        }
    }

    @Override
    public DynamicDataModel getById(final String entityName, final Long pkId) {

        try {
            //Find entity from database
            final Map<String, Object> properties =
                (Map<String, Object>) sessionFactory.getCurrentSession()
                    .get(entityName, pkId);

            log.info("Entity: {} available in database with id {} with properties {} ", entityName, pkId, properties);
            log.info("Entity: {} available in database with id {} ", entityName, pkId);

            final Map<String, Object> filteredProperties =
                properties.entrySet()
                    .stream()
                    .filter(key -> !DEFAULT_KEYS.contains(key.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            final DynamicDataModel dynamicDataModel = new DynamicDataModel();
            dynamicDataModel.setPkId(pkId);
            dynamicDataModel.setDeletedAt((LocalDateTime) properties.get("deletedAt"));
            dynamicDataModel.setEntityName(entityName);
            dynamicDataModel.setProperties(filteredProperties);

            return dynamicDataModel;
        } catch (final HibernateException e) {
            log.error(
                "Unable to find entity {} for id {} in the database with error {} ",
                entityName,
                pkId,
                e.getMessage());
            throw e;
        }
    }

    @Override
    public DynamicDataModel deleteById(final String entityName, final Long pkId) {

        try {
            final DynamicDataModel dynamicDataModel = getById(entityName, pkId);
            dynamicDataModel.setDeletedAt(LocalDateTime.now());

            final Session currentSession = sessionFactory.getCurrentSession();

            final Map<String, Object> properties = dynamicDataModel.toMap();

            log.debug("Check the entity {} exists in persistent context with id{} ", entityName, pkId);
            final boolean contains = currentSession.contains(entityName, properties);

            if (contains) {
                currentSession.save(entityName, properties);
            } else {
                log.info("Entity {} not present in the persistent context ", entityName);
                final Object merge = currentSession.merge(entityName, properties);
                currentSession.persist(entityName, merge);
            }

            log.debug("Entity {} with id {} delete from persistent context successfully ", entityName, pkId);

            return dynamicDataModel;
        } catch (final HibernateException e) {
            log.error(
                "Unable to delete entity {} for id {} in the database with error {} ",
                entityName,
                pkId,
                e.getMessage());
            throw e;
        }
    }

    @Override
    public DynamicDataModel delete(final DynamicDataModel dynamicDataModel) {

        Assert.notNull(dynamicDataModel, "dynamicModel object is mandatory");
        Assert.notNull(dynamicDataModel.getPkId(), "dynamicModel object is mandatory");
        Assert.notNull(dynamicDataModel.getEntityName(), "dynamicModel object is mandatory");

        try {

            dynamicDataModel.setDeletedAt(LocalDateTime.now());
            sessionFactory.getCurrentSession()
                .saveOrUpdate(dynamicDataModel.getEntityName(), dynamicDataModel.toMap());

            return dynamicDataModel;
        } catch (final HibernateException e) {
            log.error(
                "Unable to delete entity {} for id {} in the database with error {} ",
                dynamicDataModel.getEntityName(),
                dynamicDataModel.getPkId(),
                e.getMessage());
            throw e;
        }
    }

    @Override
    public DynamicDataModel queryOne(final String hql, final List<Object> params) {
        final Query<Map<String, Object>> query = sessionFactory.openStatelessSession().createQuery(hql);

        for (int i = 1; i <= params.size(); i++) {
            query.setParameter(i, params.get(i - 1));
        }

        final Map<String, Object> singleResult = query.getSingleResult();

        return buildDynamicModel().apply(singleResult);
    }

    @Override
    public List<DynamicDataModel> queryList(final String hql, final List<Object> params) {

        final Query<Map<String, Object>> query = sessionFactory.openStatelessSession().createQuery(hql);

        for (int i = 1; i <= params.size(); i++) {
            query.setParameter(i, params.get(i - 1));
        }

        final List<Map<String, Object>> results = query.getResultList();

        return results.stream()
            .map(buildDynamicModel())
            .collect(Collectors.toList());

    }

    private Function<Map<String, Object>, DynamicDataModel> buildDynamicModel() {
        return singleResult -> {
            final DynamicDataModel dynamicDataModel = new DynamicDataModel();
            dynamicDataModel.setPkId((Long) singleResult.get("pkId"));
            dynamicDataModel.setDeletedAt((LocalDateTime) singleResult.get("deletedAt"));
            dynamicDataModel.setEntityName((String) singleResult.get("$type$"));

            final Map<String, Object> filteredProperties =
                singleResult.entrySet()
                    .stream()
                    .filter(key -> !DEFAULT_KEYS.contains(key.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            dynamicDataModel.setProperties(filteredProperties);

            return dynamicDataModel;
        };
    }
}
