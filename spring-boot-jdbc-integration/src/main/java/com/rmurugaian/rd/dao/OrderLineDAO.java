package com.rmurugaian.rd.dao;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.rd.domain.OrderLine;
import com.rmurugaian.rd.domain.OrderLineData;

/**
 * @author rmurugaian 2018-12-27
 */
public interface OrderLineDAO {

    OrderLine save(OrderLineData orderLine);

    OrderLine findById(long id);

    ImmutableSet<OrderLine> findAll();
}
