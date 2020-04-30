package com.rmurugaian.spring.sequence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.type.LongType;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Log4j2
public class SequenceEventListener implements PreInsertEventListener {

    private static final long serialVersionUID = 2096855137271487121L;
    
    private final Map<String, List<FieldAndGenerator>> configurationCache;

    public SequenceEventListener() {
        this.configurationCache = new ConcurrentHashMap<>();
    }

    @Override
    public boolean onPreInsert(final PreInsertEvent event) {

        register(event.getSession());

        configurationCache.get(event.getEntityName())
            .forEach(fieldAndGenerator -> mutateSequence(event, fieldAndGenerator));

        return false;
    }

    private void register(final EventSource eventSource) {
        final Metamodel metamodel = eventSource.getMetamodel();
        for (final EntityType<?> entity : metamodel.getEntities()) {
            final Class<?> javaType = entity.getJavaType();
            configurationCache.computeIfAbsent(
                javaType.getName(),
                entityName -> build(eventSource, entityName, javaType));
        }
    }

    @SneakyThrows
    private void mutateSequence(final PreInsertEvent event, final FieldAndGenerator fieldAndGenerator) {

        final Object entity = event.getEntity();
        final String entityName = event.getEntityName();

        final Serializable nextSequence =
            fieldAndGenerator.getTableGenerator().generate(event.getSession(), entity);

        final Field field = fieldAndGenerator.getField();
        field.setAccessible(true);
        field.set(entity, nextSequence);

        final String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
        final Object[] state = event.getState();
        final String propertyToSet = field.getName();
        final int index = ArrayUtils.indexOf(propertyNames, propertyToSet);
        if (index >= 0) {
            state[index] = nextSequence;
        } else {
            log.error("Field '" + propertyToSet + "' not found on entity '" + entityName + "'.");
        }
    }

    private List<FieldAndGenerator> build(
        final EventSource eventSource,
        final String entityName,
        final Class<?> entityClass) {

        return getFields(entityClass)
            .stream()
            .filter(field -> field.isAnnotationPresent(Sequence.class))
            .map(field -> build(eventSource, entityName, field))
            .collect(Collectors.toList());
    }

    private FieldAndGenerator build(
        final EventSource eventSource,
        final String entityName,
        final Field field) {

        final SessionFactoryImplementor sessionFactory = eventSource.getSessionFactory();
        final ServiceRegistryImplementor serviceRegistry = sessionFactory.getServiceRegistry();
        final MetamodelImplementor metamodel = sessionFactory.getMetamodel();
        final EntityPersister entityPersister = metamodel.entityPersister(entityName);

        if (entityPersister instanceof AbstractEntityPersister) {
            final AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) entityPersister;
            final String tableName = abstractEntityPersister.getTableName();
            final String[] fieldNames = abstractEntityPersister.getPropertyColumnNames(field.getName());
            final String columnName = fieldNames.length > 0 ? fieldNames[0] : field.getName();
            final IdGenerator tableGenerator =
                build(serviceRegistry, tableName, columnName, field.getAnnotation(Sequence.class));
            return new FieldAndGenerator(field, tableGenerator);
        }
        return null;
    }

    private IdGenerator build(
        final ServiceRegistryImplementor serviceRegistry,
        final String tableName,
        final String columnName,
        final Sequence sequence) {

        final Properties properties = new Properties();
        properties.setProperty(IdentifierGenerator.GENERATOR_NAME, IdGenerator.ID_TABLE);
        properties.setProperty(IdGenerator.TARGET_TABLE, tableName);
        properties.setProperty(IdGenerator.TARGET_COLUMN, columnName);
        properties.setProperty(TableGenerator.INITIAL_PARAM, String.valueOf(sequence.initialValue()));
        properties.setProperty(TableGenerator.INCREMENT_PARAM, String.valueOf(sequence.incrementValue()));

        final Database database = MetadataExtractorIntegrator.METADATA.getDatabase();

        final IdGenerator tableGenerator = new IdGenerator();
        tableGenerator.configure(LongType.INSTANCE, properties, serviceRegistry);
        tableGenerator.registerExportables(database);
        return tableGenerator;
    }

    private List<Field> getFields(final Class<?> theClazz) {
        final List<Field> fields = new ArrayList<>();
        Class<?> clazz = theClazz;
        while (!clazz.equals(Object.class)) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    @Getter
    @AllArgsConstructor
    private static class FieldAndGenerator {
        private final Field field;
        private final IdGenerator tableGenerator;
    }

}
