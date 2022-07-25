package com.eugeniojava.javaspringcleanarchitectureexample.user.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonUserTest {
    private static Stream<String> provideParametersForIsPasswordValid() {
        return Stream.of(
            null,
            "",
            "a",
            "ab",
            "abc",
            "abcd",
            "abcde",
            "      "
        );
    }

    @ParameterizedTest(name = "isPasswordValid: {0}")
    @MethodSource("provideParametersForIsPasswordValid")
    void isPasswordValidShouldReturnFalseWhenPasswordLengthIsLessThan6(String password) {
        final var user = new CommonUser(1L, "user", password, LocalDateTime.now());

        assertFalse(user.isPasswordValid());
    }

    @Test
    void isPasswordValidShouldReturnTrueWhenPasswordLengthIsGreaterThan5() {
        final var user = new CommonUser(1L, "user", "123456", LocalDateTime.now());

        assertTrue(user.isPasswordValid());
    }
}
