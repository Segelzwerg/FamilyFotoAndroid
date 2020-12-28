package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
        this.filesQueued = new ArrayList<>();
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
     *
     * @param header contains the header arguments
     */

    @SuppressWarnings("checkstyle:EmptyForIteratorPad")
    public void upload(Header header) {
        for (Iterator<String> iterator = filesQueued.iterator(); iterator.hasNext(); ) {
            uploadFile(iterator.next(), header);
            // TODO: move this to upload call back
            iterator.remove();
        }
    }

    private void uploadFile(String file, Header header) {
        Log.d("UPLOAD", String.format("Try to upload %s", file));
        uploader.upload(file, header);
    }


}
