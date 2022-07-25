package com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out;

import com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user.User;

public interface SaveUserPort {
    User save(User user);
}
