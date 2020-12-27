package com.segelzwerg.familyfotoandroid.familyfotoservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UploaderQueueTest {

    public static final String PATH = "/test";
    private Header header;
    private UploaderQueue uploaderQueue;
    private Uploader uploader;

    @BeforeEach
    public void setUp() {
        uploader = mock(Uploader.class);
        uploaderQueue = new UploaderQueue(uploader);
        header = new Header();
    }

    @Test
    public void testUploadWithString() {
        uploaderQueue.add(PATH);
        uploaderQueue.upload(header);
        verify(uploader, times(1)).upload(PATH, header);
    }

    @Test
    public void testUploadWithFile() {
        uploaderQueue.add(new File(PATH));
        uploaderQueue.upload(header);
        verify(uploader, times(1)).upload(PATH, header);
    }
}