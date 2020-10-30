package com.segelzwerg.familyfotoandroid.familyfotoservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UploaderQueueTest {

    public static final String PATH = "/test";
    private UploaderQueue uploaderQueue;
    private Uploader uploader;

    @BeforeEach
    public void setUp() {
        uploader = mock(Uploader.class);
        uploaderQueue = new UploaderQueue(uploader);
    }

    @Test
    public void testUploadWithString() {
        uploaderQueue.add(PATH);
        uploaderQueue.upload();
        verify(uploader, times(1)).upload(PATH);
    }

    @Test
    public void testUploadWithFile() {
        uploaderQueue.add(new File(PATH));
        uploaderQueue.upload();
        verify(uploader, times(1)).upload(PATH);
    }

    @Test
    public void testUploadsEmptiesList() {
        uploaderQueue.add(new File(PATH));
        uploaderQueue.upload();
        uploaderQueue.upload();
        verify(uploader, times(1)).upload(PATH);
    }
}