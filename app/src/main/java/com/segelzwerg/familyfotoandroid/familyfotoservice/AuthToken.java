package com.segelzwerg.familyfotoandroid.familyfotoservice;

import lombok.Value;

/**
 * Authentication token created by the FamilyFotoServer.
 */
@Value
public class AuthToken {
    /**
     * The token string.
     */
    private final String token;
}
