package com.segelzwerg.familyfotoandroid.ui.activities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.segelzwerg.familyfotoandroid.familyfotoservice.FamilyFotoServerService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides a {@link FamilyFotoServerService} implementation.
 */
@Module
@InstallIn(ApplicationComponent.class)
public class LoginActivityModule {

    /**
     * Provides a Retrofit Instance.
     * @param baseUrl of the server
     * @return {@link Retrofit}
     */
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(HttpUrl baseUrl) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Provides a {@link FamilyFotoServerService} instance.
     * @param retrofit client api framework
     * @return {@link FamilyFotoServerService}
     */
    @Provides
    public static FamilyFotoServerService provideServer(Retrofit retrofit) {
        return retrofit.create(FamilyFotoServerService.class);
    }
}
