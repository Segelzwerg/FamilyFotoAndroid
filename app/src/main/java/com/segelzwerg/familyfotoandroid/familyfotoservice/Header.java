package com.segelzwerg.familyfotoandroid.familyfotoservice;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * Wrapper class for the {@link Map} of header arguments.
 */
public class Header {
    /**
     * Contains the key value pairs for the header arguments.
     */
    @Getter
    private final Map<String, String> headers;

    /**
     * Constructor.
     */
    public Header() {
        headers = new HashMap<>();
    }

    /**
     * Adds login credentials encode to the basic auth header.
     *
     * @param credentials {@link LoginCredentials}
     */
    public void addAuthentication(LoginCredentials credentials) {
        headers.put("Authorization", String.format("Basic %s", credentials.encode()));
    }

    /**
     * Adds token authentication header.
     *
     * @param userId is the user id
     * @param token  is the token string
     */
    public void addToken(int userId, String token) {
        headers.put("Authorization", String.format("Bearer %s", token));
        headers.put("user_id", String.valueOf(userId));
    }
}
