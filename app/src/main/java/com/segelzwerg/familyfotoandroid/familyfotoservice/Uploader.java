package com.segelzwerg.familyfotoandroid.familyfotoservice;

/**
 * Uploads files to a server.
 */
public interface Uploader {
    /**
     * Uploads the file to a specific server.
     * @param path to the file
     */
    void upload(String path);
}
