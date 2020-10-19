package com.rmurugaian.rd.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

/**
 * @author rmurugaian 2018-12-27
 */
@Builder(toBuilder = true)
@Data
@JsonDeserialize(builder = OrderLineData.OrderLineDataBuilder.class)
public class OrderLineData {

    private final String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderLineDataBuilder {

    }
}
