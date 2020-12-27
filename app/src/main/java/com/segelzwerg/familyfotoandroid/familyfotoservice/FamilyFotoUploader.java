package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.util.Log;

import com.segelzwerg.familyfotoandroid.imageservice.utils.FileLoaderUtil;

import java.io.File;
import java.io.IOException;

import lombok.AllArgsConstructor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Uploads a file to the family foto server.
 */
@AllArgsConstructor
public class FamilyFotoUploader implements Uploader {
    /**
     * Server where photos are being uploaded.
     */
    private final FamilyFotoServerService server;

    /**
     * Uploads the file to a specific server.
     *
     * @param header contains header arguments
     * @param path   to the file
     */
    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    public boolean upload(String path, Header header) {
        File file = FileLoaderUtil.getFile(path);
        RequestBody requestBody = RequestBody.create(file, MediaType.parse("image/*"));
        Call<Response> call = server.upload(header.getHeaders(), requestBody);
        boolean success = true;
        try {
            call.execute();
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage(), e);
            success = false;
        }
        return success;
    }
}
