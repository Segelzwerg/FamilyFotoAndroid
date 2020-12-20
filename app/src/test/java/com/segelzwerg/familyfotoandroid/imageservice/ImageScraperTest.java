package com.segelzwerg.familyfotoandroid.imageservice;

import android.os.FileObserver;

import com.segelzwerg.familyfotoandroid.familyfotoservice.UploaderQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImageScraperTest {

    public static final long LAST_CHECKED = 0L;
    public static final String DIR_PATH = "src/test/resources/";
    private static final String pathToFile = String.format("%stest-image.jpg", DIR_PATH);

    private UploaderQueue uploaderQueue;
    private ImageScraper imageScraper;


    @BeforeEach
    public void setUp() {
        uploaderQueue = mock(UploaderQueue.class);
    }

    @Test
    public void testConstruction() throws IOException {
        File file = new File(pathToFile);
        imageScraper = new ImageScraper(Paths.get(DIR_PATH), uploaderQueue, LAST_CHECKED);
        verify(uploaderQueue, times(1)).add(file);
    }

    @Test
    public void testSimplifiedConstructor() throws IOException {
        File file = new File(pathToFile);
        imageScraper = new ImageScraper(Paths.get(DIR_PATH), uploaderQueue);
        verify(uploaderQueue, times(1)).add(file);
    }

    @Test
    public void testOnCreate() throws IOException {
        imageScraper = new ImageScraper(Paths.get(DIR_PATH), uploaderQueue, LAST_CHECKED);
        imageScraper.onEvent(FileObserver.CREATE, pathToFile);
        verify(uploaderQueue, times(1)).add(pathToFile);

    }
}