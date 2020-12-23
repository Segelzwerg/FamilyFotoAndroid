package com.segelzwerg.familyfotoandroid.familyfotoservice;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Offers request to the FamilyFoto server.
 */
public interface FamilyFotoServerService {
    /**
     * Request to login current user.
     *
     * @param headers contains key value pairs of header arguments
     * @return access token
     */
    @POST("/api/token")
    Call<AuthToken> login(@HeaderMap Map<String, String> headers);
}
