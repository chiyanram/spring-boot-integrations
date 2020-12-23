package com.rmurugaian.spring.validation.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Getter
public class MessageCode {

    private final String code;
    private final MessageType type;
    private final String field;
    private final List<MessageParam> params;

    public static MessageCode error(final String theCode) {
        return msg(MessageType.ERROR, theCode, null);
    }

    public static MessageCode error(
        final String theCode,
        final String theField) {
        return msg(MessageType.ERROR, theCode, theField);
    }

    public static MessageCode error(
        final String theCode,
        final String paramCode,
        final String paramDefault) {

        return msg(theCode, paramCode, paramDefault);
    }

    public static MessageCode error(
        final String theCode,
        final String theField,
        final String paramCode,
        final String paramDefault) {

        return msg(MessageType.ERROR, theCode, theField, paramCode, paramDefault);
    }

    public static MessageCode error(
        final String theCode,
        final MessageParam... params) {

        return msg(MessageType.ERROR, theCode, null, params);
    }

    public static MessageCode error(
        final String theCode,
        final String theField,
        final MessageParam... params) {

        return msg(MessageType.ERROR, theCode, theField, params);
    }

    public static MessageCode msg(
        final MessageType type,
        final String theCode,
        final String theField,
        final String paramCode,
        final String paramDefault) {

        return msg(type, theCode, theField, new MessageParam(paramCode, paramDefault));
    }

    public static MessageCode msg(final String theCode, final String paramCode, final String paramDefault) {
        return msg(MessageType.ERROR, theCode, null, new MessageParam(paramCode, paramDefault));
    }

    public static MessageCode msg(
        final MessageType type,
        final String theCode,
        final String theField,
        final MessageParam... messageParams) {

        return MessageCode.builder()
            .code(theCode)
            .type(type)
            .field(theField)
            .params(Arrays.stream(messageParams).collect(Collectors.toList()))
            .build();
    }

    @Override
    public String toString() {
        return "MessageCode{" +
            "code='" + code + '\'' +
            ", type=" + type +
            ", field='" + field + '\'' +
            ", params=" + params +
            '}';
    }
}
