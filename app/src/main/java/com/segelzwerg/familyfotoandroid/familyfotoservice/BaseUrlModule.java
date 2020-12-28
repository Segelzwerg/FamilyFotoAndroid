package com.segelzwerg.familyfotoandroid.familyfotoservice;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import lombok.Setter;
import okhttp3.HttpUrl;

/**
 * Provides the base the url of the family foto seHOSTrver.
 */
@Module
@InstallIn(ApplicationComponent.class)
public class BaseUrlModule {
    /**
     * Port on which the server is listening.
     */
    public static final int SERVER_PORT = 5000;
    /**
     * IP address of the host.
     */
    @SuppressWarnings("PMD.AvoidUsingHardCodedIP") //this is temporary
    @Setter
    private static String host = "10.0.2.2";

    /**
     * Provides the base URL of the family foto server.
     *
     * @return {@link HttpUrl} server url
     */
    @Provides
    public static HttpUrl provideBaseUrl() {
        return HttpUrl.parse("http://" + host + ":" + SERVER_PORT);
    }
}
