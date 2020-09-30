package com.segelzwerg.familyfotoandroid.familiyfotoservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LoginCredentialsTest {
    @Test
    public void testConstructor() {
        LoginCredentials marcel = new LoginCredentials("marcel", "1234");
        assertThat(marcel.getPassword()).isEqualTo("1234");
        assertThat(marcel.getUsername()).isEqualTo("marcel");
    }
}