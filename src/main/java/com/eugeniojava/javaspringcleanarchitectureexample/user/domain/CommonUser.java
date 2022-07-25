package com.eugeniojava.javaspringcleanarchitectureexample.user.domain;

import java.time.LocalDateTime;

class CommonUser implements User {
    private final Long id;
    private final String username;
    private final String password;
    private final LocalDateTime createdAt;

    public CommonUser(Long id, String username, String password, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean isPasswordValid() {
        return password != null && password.trim().length() > 5;
    }
}
