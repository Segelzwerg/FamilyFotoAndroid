package com.segelzwerg.familyfotoandroid.familyfotoservice;

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
}
