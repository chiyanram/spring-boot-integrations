package com.rmurugaian.spring.activemq.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = Message.MessageBuilder.class)
public class Message {

    String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MessageBuilder {

    }
}
