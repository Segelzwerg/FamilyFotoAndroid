package com.segelzwerg.familyfotoandroid.familyfotoservice;


import java.util.Base64;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Wrapper for login data.
 */
@AllArgsConstructor
@Value
public class LoginCredentials {
    /**
     * User ID in some request used.
     */
    private final Integer userId;
    /**
     * The username that is passed to the login request.
     */
    private final String username;
    /**
     * The plain text password passed to login request.
     */
    private final String password;

    /**
     * Constructor in case the id is not yet none.
     *
     * @param username The username that is passed to the login request.
     * @param password The plain text password passed to login request.
     */
    public LoginCredentials(String username, String password) {
        this(null, username, password);
    }

    /**
     * Encodes the credentials with Base64.
     *
     * @return Base64 encoder of "username:password"
     */
    public String encode() {
        String authString = String.format("%s:%s", username, password);
        return createEncodedString(authString);
    }

    private String createEncodedString(String authString) {
        return getString(Base64.getEncoder(), authString);
    }

    private String getString(Base64.Encoder encoder, String authString) {
        return encoder.encodeToString(toBytes(authString));
    }

    private byte[] toBytes(String authString) {
        return authString.getBytes();
    }
}
