package com.segelzwerg.familyfotoandroid.familiyfotoservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Builds the retrofit2 client.
 */
public class RetrofitClientBuilder {

    /**
     * @param baseUrl the host url
     * @return {@link Retrofit} instance
     */
    public static Retrofit buildRetrofitInstance(HttpUrl baseUrl) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
