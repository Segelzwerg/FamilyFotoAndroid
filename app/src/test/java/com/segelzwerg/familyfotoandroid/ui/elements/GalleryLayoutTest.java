package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.Log;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;


public class GalleryLayoutTest {
    @Test
    public void testNoFilesThrowsException() {
        try(MockedStatic<Log> log = mockStatic(Log.class)) {
            GalleryLayout galleryLayout = new GalleryLayout(mock(Context.class));
            File file = new File("not/a/path.txt");
            List<File> files = Collections.singletonList(file);
            galleryLayout.addImages(files);
            String message = String.format("File does not exists: %s", file.getAbsolutePath());
            Log.e("","");
            log.verify(times(1), () -> Log.e("FILE", message));
        }
    }
}