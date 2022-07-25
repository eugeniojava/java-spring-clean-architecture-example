package com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto;

import java.time.LocalDateTime;

public record RegisterUserResponse(Long id, String username, LocalDateTime createdAt) {
}
