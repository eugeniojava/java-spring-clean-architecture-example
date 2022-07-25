package com.eugeniojava.javaspringcleanarchitectureexample.user.application.port.out;

public interface ExistsUserByUsernamePort {
    boolean existsByUsername(String username);
}
