package com.segelzwerg.familyfotoandroid.familyfotoservice;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.HttpUrl;

/**
 * Provides the base the url of the family foto server.
 */
@Module
@InstallIn(ApplicationComponent.class)
public class BaseUrlModule {
    /**
     * Port on which the server is listening.
     */
    public static final int MOCK_SERVER_PORT = 5000;

    /**
     * Provides the base URL of the family foto server.
     * @return {@link HttpUrl} server url
     */
    @Provides
    public static HttpUrl provideBaseUrl() {
        return HttpUrl.parse("http://localhost:" + MOCK_SERVER_PORT);
    }
}
