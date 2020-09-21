package com.segelzwerg.familyfotoandroid.familiyfotoservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Offers request to the FamilyFoto server.
 */
public interface FamilyFotoService {
    /**
     * Request to login current user.
     *
     * @param loginData contains username and password
     * @return access token
     */
    @POST("/api/login")
    Call<AuthToken> login(@Body LoginCredentials loginData);
}
