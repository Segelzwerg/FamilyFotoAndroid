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
     * @param path   to the file
     * @param header contains header arguments
     */
    @SuppressWarnings("PMD.OnlyOneReturn")
    @Override
    // update to https://gist.github.com/shakil807g/535dc99f793d28f877db
    public void upload(String path, Header header) {
        File file = FileLoaderUtil.getFile(path);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"));
        MediaType type = MediaType.parse("image/jpeg");
        builder.addFormDataPart("files", getName(file), RequestBody.create(type, file));
        MultipartBody body = builder.build();
        Call<Response> call = server.upload(header.getHeaders(), body);
        call.enqueue(new UploadCallback());
    }

    @NotNull
    private String getName(File file) {
        return file.getName();
    }
}
