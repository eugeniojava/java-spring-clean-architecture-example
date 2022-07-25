package com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in;

import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserRequest;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserResponse;

public interface RegisterUserUseCase {
    RegisterUserResponse execute(RegisterUserRequest registerUserRequest);
}
