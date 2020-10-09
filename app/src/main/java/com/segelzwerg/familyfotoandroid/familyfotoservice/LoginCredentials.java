package com.segelzwerg.familyfotoandroid.familyfotoservice;

import lombok.Value;

/**
 * Wrapper for login data.
 */
@Value
public class LoginCredentials {
    /**
     * The username that is passed to the login request.
     */
    private final String username;
    /**
     * The plain text password passed to login request.
     */
    private final String password;
}
