package com.segelzwerg.familyfotoandroid.familyfotoservice;

import lombok.Value;

/**
 * Simple response from the family foto server.
 */
@Value
public class Response {
    /**
     * Contains the status code.
     */
    private final String success;
}
