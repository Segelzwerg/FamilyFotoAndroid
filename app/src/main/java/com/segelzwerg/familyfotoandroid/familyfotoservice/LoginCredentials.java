package com.segelzwerg.familyfotoandroid.familyfotoservice;

import java.util.Base64;

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

    /**
     * Encodes the credentials with Base64.
     *
     * @return Base64 encoder of "username:password"
     */
    public String encode() {
        String authString = String.format("%s:%s", username, password);
        return Base64.getEncoder().encodeToString(authString.getBytes());
    }
}
