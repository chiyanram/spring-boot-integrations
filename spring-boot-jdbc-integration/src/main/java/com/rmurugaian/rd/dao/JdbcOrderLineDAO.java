package com.rmurugaian.rd.dao;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.rd.domain.OrderLine;
import com.rmurugaian.rd.domain.OrderLineData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Types;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author rmurugaian 2018-12-27
 */
@Repository
public class JdbcOrderLineDAO implements OrderLineDAO {


    private static final String CREATE_ORDER_LINE =
            "INSERT INTO OrderLine(name,createdDate) values (:name,:createdDate)";
    private static final String FIND_BY_ID = "select * from OrderLine where id = :id";
    private static final String FIND_ALL = "select * from OrderLine";

    private final RowMapper<OrderLine> mapper = new OrderLineRowMapper();

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public JdbcOrderLineDAO(final NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public OrderLine save(final OrderLineData orderLine) {
        Assert.notNull(orderLine, "orderLine");
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        final LocalDateTime createdDate = LocalDateTime.now();

        final MapSqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("name", orderLine.getName(), Types.NVARCHAR)
                .addValue("createdDate", createdDate, Types.TIME);

        namedParameterJdbcOperations
                .update(CREATE_ORDER_LINE, paramSource, keyHolder, new String[]{"id"});

        final long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return OrderLine.builder()
                .id(id)
                .name(orderLine.getName())
                .createdDate(createdDate)
                .build();
    }

    @Override
    public OrderLine findById(final long id) {

        return namedParameterJdbcOperations.queryForObject(FIND_BY_ID, new MapSqlParameterSource("id", id), mapper);
    }

    @Override
    public ImmutableSet<OrderLine> findAll() {
        return ImmutableSet.copyOf(namedParameterJdbcOperations.query(FIND_ALL, mapper));
    }
}
