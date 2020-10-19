package com.rmurugaian.spring.validation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -7127974019991101940L;
    private final HttpStatus status;
    private final MessageCode errorMessage;

    public ValidationException(
        final HttpStatus status,
        final MessageCode errorMessage) {

        super(errorMessage.toString());
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public ValidationException(
        final HttpStatus status,
        final String messageCode) {

        this(status, MessageCode.error(messageCode));
    }

    @Override
    public String toString() {
        return "DijtaException{" +
            "status=" + status +
            ", errorMessage=" + errorMessage +
            '}';
    }
}
