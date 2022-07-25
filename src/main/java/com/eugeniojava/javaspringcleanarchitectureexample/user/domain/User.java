package com.eugeniojava.javaspringcleanarchitectureexample.user.domain;

import java.time.LocalDateTime;

public interface User {
    Long getId();

    String getUsername();

    String getPassword();

    LocalDateTime getCreatedAt();

    boolean isPasswordValid();
}
