package com.rmurugaian.rd.dao;

import com.rmurugaian.rd.domain.OrderLine;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author rmurugaian 2018-12-27
 */
public class OrderLineRowMapper implements RowMapper<OrderLine> {
    @Override
    public OrderLine mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final Timestamp modifiedDateDB = rs.getTimestamp("modifiedDate");
        DateTime modifiedDate = null;
        if (modifiedDateDB != null) {
            modifiedDate = new DateTime(modifiedDateDB, DateTimeZone.UTC);
        }

        return OrderLine.builder()
                .id(rs.getLong("id"))
                .name(rs.getNString("name"))
                .createdDate(rs.getTimestamp("createdDate").toLocalDateTime())
                .modifiedDate(modifiedDate)
                .build();
    }
}
