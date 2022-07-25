package com.eugeniojava.javaspringcleanarchitectureexample.user.application.service;

import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.in.dto.RegisterUserRequest;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out.ExistsUserByUsernamePort;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out.SaveUserPort;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception.InvalidPasswordException;
import com.eugeniojava.javaspringcleanarchitectureexample.user.application.service.exception.UsernameAlreadyExistsException;
import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.User;
import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {
    @Mock
    private ExistsUserByUsernamePort existsUserByUsernamePort;

    @Mock
    private User user;

    @Mock
    private UserFactory userFactory;

    @Mock
    private SaveUserPort saveUserPort;

    @InjectMocks
    private RegisterUserService registerUserService;

    @Test
    void executeShouldThrowUsernameAlreadyExistsExceptionWhenUsernameAlreadyExists() {
        final var registerUserRequest = new RegisterUserRequest("username", "password");
        given(existsUserByUsernamePort.existsByUsername(any())).willReturn(true);

        assertThrows(UsernameAlreadyExistsException.class, () -> registerUserService.execute(registerUserRequest));
    }

    @Test
    void executeShouldThrowInvalidPasswordExceptionWhenPasswordIsInvalid() {
        final var registerUserRequest = new RegisterUserRequest("username", "password");
        given(existsUserByUsernamePort.existsByUsername(any())).willReturn(false);
        given(userFactory.create(any(), any(), any(), any())).willReturn(user);
        given(user.isPasswordValid()).willReturn(false);

        assertThrows(InvalidPasswordException.class, () -> registerUserService.execute(registerUserRequest));
    }

    @Test
    void executeShouldSaveUserWhenAllValidationsPassed() {
        final var registerUserRequest = new RegisterUserRequest("username", "password");
        given(existsUserByUsernamePort.existsByUsername(any())).willReturn(false);
        given(userFactory.create(any(), any(), any(), any())).willReturn(user);
        given(user.isPasswordValid()).willReturn(true);
        given(saveUserPort.save(any())).willReturn(user);

        registerUserService.execute(registerUserRequest);

        verify(saveUserPort, times(1)).save(user);
    }
}
