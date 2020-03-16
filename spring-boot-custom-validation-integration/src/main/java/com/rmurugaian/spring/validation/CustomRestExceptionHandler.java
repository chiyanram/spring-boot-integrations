package com.rmurugaian.spring.validation;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(CUstomException.class)
    protected ResponseEntity<ErrorResponse> handleDijtaBusinessException(final CUstomException exception) {
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
