package com.segelzwerg.familyfotoandroid.familyfotoservice;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.HttpUrl;

@Module
@InstallIn(ApplicationComponent.class)
public class BaseUrlModule {
    public static final int MOCK_SERVER_PORT = 5000;

    @Provides
    public static HttpUrl provideBaseUrl() {
        return HttpUrl.parse("http://localhost:" + MOCK_SERVER_PORT);
    }
}
