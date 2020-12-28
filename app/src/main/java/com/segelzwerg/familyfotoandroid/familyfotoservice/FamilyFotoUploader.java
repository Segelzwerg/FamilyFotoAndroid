package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.util.Log;

import com.segelzwerg.familyfotoandroid.imageservice.utils.FileLoaderUtil;
import com.segelzwerg.familyfotoandroid.ui.UploadCallback;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.segelzwerg.familyfotoandroid.imageservice.utils.ImageProperties.getMimeType;

/**
 * Uploads a file to the family photo server.
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
        List<String> paths = Arrays.asList(path);
        uploadFiles(paths, header);
    }

    /**
     * Uploads multiple files.
     *
     * @param paths  list of file paths
     * @param header contains header arguments. E.g. must contain token auth,
     */
    @Override
    public void uploadFiles(List<String> paths, Header header) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"));
        paths.forEach(path -> {
            File file = FileLoaderUtil.getFile(path);
            MediaType type = null;
            try {
                type = MediaType.parse(getMimeType(file));
            } catch (IOException e) {
                Log.e("FILE", e.getMessage(), e);
            }
            builder.addFormDataPart("files", getName(file), RequestBody.create(type, file));
        });
        MultipartBody body = builder.build();
        Call<Response> call = server.upload(header.getHeaders(), body);
        call.enqueue(new UploadCallback());
    }

    @NotNull
    private String getName(File file) {
        return file.getName();
    }
}
