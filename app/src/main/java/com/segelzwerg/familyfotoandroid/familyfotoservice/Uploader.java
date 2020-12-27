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
     * @return if the upload was successful
     */
    boolean upload(String path, Header header);
}
