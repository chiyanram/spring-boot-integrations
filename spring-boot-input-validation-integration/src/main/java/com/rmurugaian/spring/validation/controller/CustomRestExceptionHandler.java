package com.rmurugaian.spring.validation.controller;

import com.rmurugaian.spring.validation.MessageConstants;
import com.rmurugaian.spring.validation.domain.ErrorResponse;
import com.rmurugaian.spring.validation.exception.MessageCode;
import com.rmurugaian.spring.validation.exception.ValidationException;
import com.rmurugaian.spring.validation.validator.CustomValidationParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponse> handleDijtaBusinessException(final ValidationException exception) {
        log.error("Business error: " + exception.getMessage());
        return buildErrorResponse(exception.getStatus(), Collections.singletonList(exception.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(final MethodArgumentNotValidException exception) {
        final List<MessageCode> errors = CustomValidationParser.getValidationErrors(exception);
        log.error("Validation error: {}", errors);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleGenericException(final Exception exception) {
        log.error("Runtime exception: ", exception);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstants.TECHNICAL_PROBLEM);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
        final HttpStatus httpStatus,
        final String messageCode) {
        return ResponseEntity
            .status(httpStatus)
            .body(ErrorResponse.error(MessageCode.error(messageCode)));
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
        final HttpStatus httpStatus,
        final List<MessageCode> errors) {

        return ResponseEntity
            .status(httpStatus)
            .body(ErrorResponse.errors(errors));
    }

}
