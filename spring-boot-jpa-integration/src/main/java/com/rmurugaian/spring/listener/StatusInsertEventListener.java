package com.rmurugaian.spring.listener;

import com.rmurugaian.spring.domain.StatusEntity;
import com.rmurugaian.spring.util.Mutator;
import lombok.extern.log4j.Log4j2;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

@Log4j2
public class StatusInsertEventListener implements PreInsertEventListener {
    private static final long serialVersionUID = 6835180783549033490L;

    public static final StatusInsertEventListener INSTANCE = new StatusInsertEventListener();

    private static final String STATUS = "status";
    private static final String NEW_STATUS = "NEW";

    @Override
    public boolean onPreInsert(final PreInsertEvent event) {
        if (event.getEntity() instanceof StatusEntity) {
            ((StatusEntity) event.getEntity()).setStatus(NEW_STATUS);
            final String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
            Mutator.mutateState(propertyNames, event.getState(), STATUS, NEW_STATUS);
        }
        return false;
    }

}
