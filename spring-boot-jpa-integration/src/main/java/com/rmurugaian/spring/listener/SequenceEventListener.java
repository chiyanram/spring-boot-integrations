package com.rmurugaian.spring.listener;

import com.rmurugaian.spring.sequence.IdGenerator;
import com.rmurugaian.spring.sequence.Sequence;
import com.rmurugaian.spring.util.Mutator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.hibernate.type.LongType;

import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Log4j2
public class SequenceEventListener implements PreInsertEventListener {

    private static final long serialVersionUID = 2096855137271487121L;

    private final Metadata metadata;
    private final SessionFactoryImplementor sessionFactory;
    private final SessionFactoryServiceRegistry serviceRegistry;
    private final Map<String, List<FieldAndGenerator>> configurationCache;

    public SequenceEventListener(
        final Metadata metadata,
        final SessionFactoryImplementor sessionFactory,
        final SessionFactoryServiceRegistry serviceRegistry) {

        this.metadata = metadata;
        this.sessionFactory = sessionFactory;
        this.serviceRegistry = serviceRegistry;
        this.configurationCache = new ConcurrentHashMap<>();

    }

    @Override
    public boolean onPreInsert(final PreInsertEvent event) {
        computeCache();

        configurationCache.get(event.getEntityName())
            .forEach(fieldAndGenerator -> mutateSequence(event, fieldAndGenerator));

        return false;
    }

    private void computeCache() {
        final MetamodelImplementor metamodel = sessionFactory.getMetamodel();
        for (final EntityType<?> entity : metamodel.getEntities()) {
            final Class<?> javaType = entity.getJavaType();
            configurationCache.computeIfAbsent(
                javaType.getName(),
                entityName -> build(entityName, javaType));
        }
    }

    @SneakyThrows
    private void mutateSequence(final PreInsertEvent event, final FieldAndGenerator fieldAndGenerator) {
        final Object entity = event.getEntity();
        final Serializable nextSequence = fieldAndGenerator.getTableGenerator().generate(event.getSession(), entity);

        final Field field = fieldAndGenerator.getField();
        field.setAccessible(true);
        field.set(entity, nextSequence);

        final String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
        Mutator.mutateState(propertyNames, event.getState(), field.getName(), nextSequence);
    }

    private List<FieldAndGenerator> build(final String entityName, final Class<?> entityClass) {

        return Arrays.stream(FieldUtils.getAllFields(entityClass))
            .filter(field -> field.isAnnotationPresent(Sequence.class))
            .map(field -> build(entityName, field))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    private Optional<FieldAndGenerator> build(final String entityName, final Field field) {
        final MetamodelImplementor metamodel = sessionFactory.getMetamodel();
        final EntityPersister entityPersister = metamodel.entityPersister(entityName);

        if (entityPersister instanceof AbstractEntityPersister) {
            final AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) entityPersister;
            final String tableName = abstractEntityPersister.getTableName();
            final String[] fieldNames = abstractEntityPersister.getPropertyColumnNames(field.getName());
            final String columnName = fieldNames.length > 0 ? fieldNames[0] : field.getName();
            final IdGenerator tableGenerator =
                build(tableName, columnName, field.getAnnotation(Sequence.class));
            return Optional.of(new FieldAndGenerator(field, tableGenerator));
        }
        return Optional.empty();
    }

    private IdGenerator build(final String tableName, final String columnName, final Sequence sequence) {

        final Properties properties = new Properties();
        properties.setProperty(IdentifierGenerator.GENERATOR_NAME, IdGenerator.ID_TABLE);
        properties.setProperty(IdGenerator.TARGET_TABLE, tableName);
        properties.setProperty(IdGenerator.TARGET_COLUMN, columnName);
        properties.setProperty(TableGenerator.INITIAL_PARAM, String.valueOf(sequence.initialValue()));
        properties.setProperty(TableGenerator.INCREMENT_PARAM, String.valueOf(sequence.incrementValue()));

        final Database database = metadata.getDatabase();

        final IdGenerator tableGenerator = new IdGenerator();
        tableGenerator.configure(LongType.INSTANCE, properties, serviceRegistry);
        tableGenerator.registerExportables(database);
        return tableGenerator;
    }

    @Getter
    @AllArgsConstructor
    private static class FieldAndGenerator {
        private final Field field;
        private final IdGenerator tableGenerator;
    }

}
