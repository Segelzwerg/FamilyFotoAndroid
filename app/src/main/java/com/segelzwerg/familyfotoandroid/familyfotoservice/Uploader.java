package com.segelzwerg.familyfotoandroid.familyfotoservice;

import java.util.List;

/**
 * Uploads files to a server.
 */
public interface Uploader {
    /**
     * Uploads the file to a specific server.
     *
     * @param path   to the file
     * @param header contains header arguments
     */
    void upload(String path, Header header);

    void uploadFiles(List<String> paths, Header header);
}
