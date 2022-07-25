package com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
