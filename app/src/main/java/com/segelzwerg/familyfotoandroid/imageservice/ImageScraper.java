package com.segelzwerg.familyfotoandroid.imageservice;

import android.os.FileObserver;
import android.util.Log;

import androidx.annotation.Nullable;

import com.segelzwerg.familyfotoandroid.familyfotoservice.UploaderQueue;

import java.io.File;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Scraps the devices for new files.
 */
public class ImageScraper  extends FileObserver {

    /**
     * Queue for collecting images to be uploaded.
     */
    private final transient UploaderQueue uploaderQueue;
    /**
     * Path were to watch for new images.
     */
    private final transient Path path;
    /**
     * The last time the scraper looked for new files.
     */
    private transient long lastChecked;

    /**
     * Equivalent to FileObserver(path, FileObserver.ALL_EVENTS).
     *
     * @param path to watch for scraping.
     * @param uploaderQueue the queue were files are wait for their upload
     * @param lastChecked the last time the system was checked
     */
    public ImageScraper(Path path, UploaderQueue uploaderQueue, long lastChecked) {
        super(String.valueOf(path));
        this.path = path;
        this.uploaderQueue = uploaderQueue;
        this.lastChecked = lastChecked;
        changesSinceLastTime().forEach(uploaderQueue::add);
        this.lastChecked = Calendar.getInstance().getTime().getTime();
    }

    /**
     * Adds the images added or modified to the update list.
     * @param event The type of event which happened
     * @param path  The path, relative to the main monitored file or directory,
     *              of the file or directory which triggered the event.  This value can
     *              be {@code null} for certain events, such as {@link #MOVE_SELF}.
     */
    @Override
    public void onEvent(int event, @Nullable String path) {
            if (event == FileObserver.CREATE) {
                uploaderQueue.add(path);
                lastChecked = Calendar.getInstance().getTime().getTime();
                Log.d("FILE", String.format("File created, add to queue: %s", path));
            }
    }

    private List<File> changesSinceLastTime() {
        return Stream.of(new File(String.valueOf(path)).listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.lastModified() > lastChecked)
                .collect(Collectors.toList());
    }
}
