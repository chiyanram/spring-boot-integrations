package com.rmurugaian.rd.domain;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

/**
 * @author rmurugaian 2018-12-27
 */
@Builder(toBuilder = true)
@Data
public class OrderLine {

    private final Long id;
    private final String name;
    private final LocalDateTime createdDate;
    private final DateTime modifiedDate;
}
