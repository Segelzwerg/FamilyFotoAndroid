package com.segelzwerg.familyfotoandroid.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.FamilyFotoServerService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class LoginActivityModule {

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

    @Provides
    public static FamilyFotoServerService provideServer(Retrofit retrofit) {
        return retrofit.create(FamilyFotoServerService.class);
    }
}
