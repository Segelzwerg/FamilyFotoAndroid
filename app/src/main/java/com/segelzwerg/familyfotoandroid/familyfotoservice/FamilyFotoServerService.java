package com.segelzwerg.familyfotoandroid.familyfotoservice;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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

    /**
     * Uploads a photo to the server.
     *
     * @param headers contains key value pairs of header arguments
     * @param body    to be uploaded
     * @return {@link Response}
     */
    @POST("/api/upload")
    Call<Response> upload(@HeaderMap Map<String, String> headers,
                          @Body RequestBody body);
}
