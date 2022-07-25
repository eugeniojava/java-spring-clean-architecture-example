package com.eugeniojava.javaspringcleanarchitectureexample.user.domain.user;

import java.time.LocalDateTime;

public interface UserFactory {
    User create(Long id, String username, String password, LocalDateTime createdAt);
}
