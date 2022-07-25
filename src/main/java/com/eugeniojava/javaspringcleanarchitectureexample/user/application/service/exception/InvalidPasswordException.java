package com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
