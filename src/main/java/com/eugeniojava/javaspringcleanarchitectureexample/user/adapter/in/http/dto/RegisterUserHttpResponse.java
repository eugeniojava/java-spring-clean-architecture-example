package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.dto;

import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserResponse;
import java.time.LocalDateTime;

public record RegisterUserHttpResponse(Long id, String username, LocalDateTime createdAt) {
    public static RegisterUserHttpResponse fromDomain(RegisterUserResponse registerUserResponse) {
        return new RegisterUserHttpResponse(
            registerUserResponse.id(),
            registerUserResponse.username(),
            registerUserResponse.createdAt()
        );
    }
}
