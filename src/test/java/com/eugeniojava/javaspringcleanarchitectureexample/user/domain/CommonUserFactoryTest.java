package com.eugeniojava.javaspringcleanarchitectureexample.user.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommonUserFactoryTest {
    private final UserFactory userFactory = new CommonUserFactory();

    @Test
    void createShouldReturnAMatchingInstanceOfUser() {
        final var id = 1L;
        final var username = "username";
        final var password = "123456";
        final var now = LocalDateTime.now();

        final var user = userFactory.create(id, username, password, now);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals(now, user.getCreatedAt());
    }
}
