package com.rmurugaian.spring.listener;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.spring.domain.AuditEntity;
import com.rmurugaian.spring.util.Mutator;
import lombok.extern.log4j.Log4j2;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDateTime;

@Log4j2
public class CreatedTimeInterceptor extends EmptyInterceptor {

    public static final CreatedTimeInterceptor INSTANCE = new CreatedTimeInterceptor();

    private static final ImmutableSet<String> COLUMNS = ImmutableSet.of("createdAt", "changedAt");

    private static final long serialVersionUID = 2490271921226731112L;

    @Override
    public boolean onSave(
        final Object entity,
        final Serializable id,
        final Object[] state,
        final String[] propertyNames,
        final Type[] types) {

        if (entity instanceof AuditEntity) {
            COLUMNS.forEach(column -> Mutator.mutateState(propertyNames, state, column, LocalDateTime.now()));
        }

        return true;
    }
}
