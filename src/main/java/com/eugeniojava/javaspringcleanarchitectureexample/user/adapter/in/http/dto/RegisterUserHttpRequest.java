package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.dto;

import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserRequest;
import javax.validation.constraints.NotBlank;

public record RegisterUserHttpRequest(@NotBlank String username, @NotBlank String password) {
    public static RegisterUserRequest toDomain(RegisterUserHttpRequest registerUserHttpRequest) {
        return new RegisterUserRequest(registerUserHttpRequest.username(), registerUserHttpRequest.password());
    }
}
