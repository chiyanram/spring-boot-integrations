package com.rmurugaian.rd.api;

import com.google.common.collect.ImmutableSet;
import com.rmurugaian.rd.dao.OrderLineDAO;
import com.rmurugaian.rd.domain.OrderLine;
import com.rmurugaian.rd.domain.OrderLineData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author rmurugaian 2018-12-27
 */
@RestController
@RequestMapping("/orderlines")
public class OrderLineRestController {

    private final OrderLineDAO orderLineDAO;

    public OrderLineRestController(final OrderLineDAO orderLineDAO) {
        this.orderLineDAO = orderLineDAO;
    }

    @PostMapping
    public ResponseEntity<OrderLine> save(@RequestBody final OrderLineData orderLineData) {
        try {
            final OrderLine orderLine = orderLineDAO.save(orderLineData);
            return ResponseEntity.created(URI.create("/")).body(orderLine);
        } catch (final RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLine> findByID(@PathVariable final long id) {

        try {
            final OrderLine orderLine = orderLineDAO.findById(id);
            return ResponseEntity.ok(orderLine);
        } catch (final RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<ImmutableSet<OrderLine>> findAll() {

        return ResponseEntity.ok(orderLineDAO.findAll());
    }

}
