package com.eugeniojava.javaspringcleanarchitectureexample.user.application.service;

import com.eugeniojava.javaspringcleanarchitectureexample.common.annotation.UseCase;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.RegisterUserUseCase;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserRequest;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserResponse;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out.ExistsUserByUsernamePort;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out.SaveUserPort;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception.InvalidPasswordException;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception.UsernameAlreadyExistsException;
import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.UserFactory;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
class RegisterUserService implements RegisterUserUseCase {
    private final ExistsUserByUsernamePort existsUserByUsernamePort;
    private final UserFactory userFactory;
    private final SaveUserPort saveUserPort;

    @Override
    public RegisterUserResponse execute(RegisterUserRequest registerUserRequest) {
        if (existsUserByUsernamePort.existsByUsername(registerUserRequest.username())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        final var user = userFactory.create(
            null,
            registerUserRequest.username(),
            registerUserRequest.password(),
            LocalDateTime.now()
        );
        if (!user.isPasswordValid()) {
            throw new InvalidPasswordException("Password is invalid");
        }
        final var createdUser = saveUserPort.save(user);
        return new RegisterUserResponse(createdUser.getId(), createdUser.getUsername(), createdUser.getCreatedAt());
    }
}
