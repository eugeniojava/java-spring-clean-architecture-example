package com.eugeniojava.javaspringcleanarchitectureexample.user.domain;

import com.eugeniojava.javaspringcleanarchitectureexample.common.annotation.Factory;
import java.time.LocalDateTime;

@Factory
class CommonUserFactory implements UserFactory {
    @Override
    public User create(Long id, String username, String password, LocalDateTime createdAt) {
        return new CommonUser(id, username, password, createdAt);
    }
}
