package com.segelzwerg.familyfotoandroid.ui;

import android.util.Log;

import com.segelzwerg.familyfotoandroid.familyfotoservice.Response;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Callback for upload requests.
 */
public class UploadCallback implements Callback<Response> {
    @Override
    public final void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        Log.d("UPLOAD", "Successful upload.");
    }

    @Override
    public final void onFailure(Call<Response> call, Throwable throwable) {
        Log.e("UPLOAD", "Failed to upload", throwable);
    }
}
