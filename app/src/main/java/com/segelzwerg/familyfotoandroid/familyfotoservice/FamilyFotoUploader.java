package com.segelzwerg.familyfotoandroid.familyfotoservice;

import com.segelzwerg.familyfotoandroid.imageservice.utils.FileLoaderUtil;
import com.segelzwerg.familyfotoandroid.ui.UploadCallback;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import lombok.AllArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"));
        MediaType type = MediaType.parse("image/jpeg");
        builder.addFormDataPart("files", getName(file), RequestBody.create(type, file));
        MultipartBody body = builder.build();
        Call<Response> call = server.upload(header.getHeaders(), body);
        call.enqueue(new UploadCallback());
        return true;
    }

    @NotNull
    private String getName(File file) {
        return file.getName();
    }
}
