package com.segelzwerg.familyfotoandroid.familyfotoservice;

/**
 * Uploads a file to the family foto server.
 */
public class FamilyFotoUploader implements Uploader {
    /**
     * Uploads the file to a specific server.
     *
     * @param path to the file
     */
    @Override
    public boolean upload(String path) {
        return true;
    }
}
