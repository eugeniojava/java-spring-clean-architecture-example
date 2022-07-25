package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.exception.handler;

import com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.exception.handler.dto.ErrorResponse;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception.InvalidPasswordException;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String MALFORMED_JSON_MESSAGE = "Malformed JSON request";

    @Value("${exception-handling.debug-message-enabled}")
    private boolean debugMessageEnabled;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        return getResponseEntityErrorResponseOf(
            HttpStatus.BAD_REQUEST,
            MALFORMED_JSON_MESSAGE,
            ex.getMessage(),
            null
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        return getResponseEntityErrorResponseOf(
            HttpStatus.BAD_REQUEST,
            MALFORMED_JSON_MESSAGE,
            ex.getMessage(),
            ex.getBindingResult().getFieldErrors()
        );
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return getResponseEntityErrorResponseOf(
            HttpStatus.CONFLICT,
            ex.getMessage(),
            null,
            null
        );
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException ex) {
        return getResponseEntityErrorResponseOf(
            HttpStatus.BAD_REQUEST,
            ex.getMessage(),
            null,
            null
        );
    }

    private ResponseEntity<Object> getResponseEntityErrorResponseOf(
        HttpStatus httpStatus,
        String message,
        String debugMessage,
        List<FieldError> fieldErrors
    ) {
        return ResponseEntity.status(httpStatus)
            .body(getErrorResponseOf(httpStatus, message, debugMessage, fieldErrors));
    }

    private ErrorResponse getErrorResponseOf(
        HttpStatus httpStatus,
        String message,
        String debugMessage,
        List<FieldError> fieldErrors
    ) {
        if (!debugMessageEnabled) {
            debugMessage = null;
        }
        var errorResponse = new ErrorResponse(LocalDateTime.now(), httpStatus.value(), message, debugMessage);
        if (fieldErrors != null) {
            fieldErrors.forEach(fe -> errorResponse.addValidationError(fe.getField(), fe.getDefaultMessage()));
        }
        return errorResponse;
    }
}
