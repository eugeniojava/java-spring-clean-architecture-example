package com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http;

import com.eugeniojava.javaspringcleanarchitectureexample.common.annotation.HttpAdapter;
import com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.dto.RegisterUserHttpRequest;
import com.eugeniojava.javaspringcleanarchitectureexample.user.adapter.in.http.dto.RegisterUserHttpResponse;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.RegisterUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.Valid;

@HttpAdapter(path = "/api/users")
@AllArgsConstructor
class RegisterUserController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RegisterUserHttpResponse execute(@RequestBody @Valid RegisterUserHttpRequest registerUserHttpRequest) {
        return RegisterUserHttpResponse.fromDomain(
            registerUserUseCase.execute(RegisterUserHttpRequest.toDomain(registerUserHttpRequest))
        );
    }
}
