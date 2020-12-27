package com.segelzwerg.familyfotoandroid.familyfotoservice;

import org.junit.jupiter.api.Test;

import okhttp3.HttpUrl;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BaseUrlModuleTest {

    @Test
    public void provideBaseUrl() {
        HttpUrl baseUrl = BaseUrlModule.provideBaseUrl();
        assertThat(baseUrl).isEqualTo(HttpUrl.parse("http://10.0.2.2:5000"));
    }
}