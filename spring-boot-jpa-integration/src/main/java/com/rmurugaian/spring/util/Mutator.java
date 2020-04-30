package com.rmurugaian.spring.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;

@Log4j2
public final class Mutator {

    public static void mutateState(
        final String[] propertyNames,
        final Object[] state,
        final String propertyToSet,
        final Object newSate) {

        final int index = ArrayUtils.indexOf(propertyNames, propertyToSet);
        
        if (index >= 0) {
            state[index] = newSate;
        } else {
            log.error("Field '" + propertyToSet + "' not found on entity '" + "entityName" + "'.");
        }
    }

    private Mutator() {
    }

}
