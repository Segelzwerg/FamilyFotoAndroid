package com.segelzwerg.familyfotoandroid.familyfotoservice;

import lombok.Value;

/**
 * Response containing an user id and a token.
 */
@Value
public class AuthTokenResponse {
    /**
     * Token returned from the server
     */
    private AuthToken token;
    /**
     * Corresponding id of the user.
     */
    private int userId;
}
