package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.exception.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final String debugMessage;
    private List<ValidationError> errors;

    public ErrorResponse(LocalDateTime timestamp, int status, String message, String debugMessage) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public void addValidationError(String field, String message) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }

    record ValidationError(String field, String message) {
    }
}
