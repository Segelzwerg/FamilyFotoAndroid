package com.segelzwerg.familyfotoandroid.familyfotoservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Offers request to the FamilyFoto server.
 */
public interface FamilyFotoServerService {
    /**
     * Request to login current user.
     *
     * @param loginData contains username and password
     * @return access token
     */
    @POST("/api/token")
    Call<AuthToken> login(@Body LoginCredentials loginData);
}
