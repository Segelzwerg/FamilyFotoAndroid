package com.segelzwerg.familyfotoandroid.familyfotoservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;


public class LoginCredentialsTest {

    private LoginCredentials credentials;

    @BeforeEach
    public void setUp() {
        credentials = new LoginCredentials("marcel", "1234");
    }

    @Test
    public void testConstructor() {
        assertThat(credentials.getPassword()).isEqualTo("1234");
        assertThat(credentials.getUsername()).isEqualTo("marcel");
    }

    @Test
    public void testEncode() {
        String encodedString = Base64.getEncoder().encodeToString("marcel:1234".getBytes());
        assertThat(credentials.encode()).isEqualTo(encodedString);
    }
}