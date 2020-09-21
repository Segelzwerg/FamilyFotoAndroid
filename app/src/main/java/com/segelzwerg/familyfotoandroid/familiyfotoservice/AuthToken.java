package com.segelzwerg.familyfotoandroid.familiyfotoservice;

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
