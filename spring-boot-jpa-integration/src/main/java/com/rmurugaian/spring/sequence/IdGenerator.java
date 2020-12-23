package com.rmurugaian.spring.sequence;

import org.hibernate.id.enhanced.TableGenerator;

import java.util.Properties;

public class IdGenerator extends TableGenerator {

    public static final String ID_TABLE = "sequence_id";
    public static final String ID_GENERATOR = "com.rmurugaian.spring.sequence.IdGenerator";
    public static final String TARGET_TABLE = "target_table";
    public static final String TARGET_COLUMN = "target_column";

    @Override
    protected String determineSegmentValue(final Properties params) {
        return params.getProperty(TARGET_TABLE).concat(".").concat(params.getProperty(TARGET_COLUMN));
    }

}
