package com.segelzwerg.familyfotoandroid.familyfotoservice;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Collects files to be uploaded.
 */
public class UploaderQueue {
    /**
     * Uploader to the server.
     */
    private final transient Uploader uploader;
    /**
     * List of files to be uploaded.
     */
    private final transient List<String> filesQueued;

    /**
     * Constructor.
     * @param uploader loads files up to the server.
     */
    public UploaderQueue(Uploader uploader) {
        this.uploader = uploader;
        this.filesQueued = new LinkedList<>();
    }

    /**
     * Adds a file to upload list.
     * @param path to the file
     */
    public void add(String path) {
        filesQueued.add(path);
    }

    /**
     * Adds a file to the upload list.
     * @param file file to be uploaded
     */
    public void add(File file) {
        add(file.getAbsolutePath());
    }

    /**
     * Tells the uploader to send all files and removes them.
     */
    public void upload() {
        filesQueued.forEach(this::uploadFile);
    }

    private void uploadFile(String file) {
        uploader.upload(file);
        filesQueued.remove(file);
    }


}
