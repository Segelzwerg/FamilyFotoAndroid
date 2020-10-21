package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.segelzwerg.familyfotoandroid.imageservice.PicassoAdapter;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


public class GalleryLayoutTest {
    @Test
    public void testNoFilesThrowsException() {
        try (MockedStatic<Log> log = mockStatic(Log.class)) {
            GalleryLayout galleryLayout = new GalleryLayout(mock(Context.class));
            File file = new File("not/a/path.txt");
            List<File> files = Collections.singletonList(file);
            galleryLayout.addImages(files);
            String message = String.format("File does not exists: %s", file.getAbsolutePath());
            Log.e("", "");
            log.verify(times(1), () -> Log.e("FILE", message));
        }
    }

    @Test
    public void testImageAdd() {
        Context context = mock(Context.class);
        GalleryLayout galleryLayout = new GalleryLayout(context);
        File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        when(file.getAbsolutePath()).thenReturn("/");
        MockedStatic<PicassoAdapter> picassoAdapter = mockStatic(PicassoAdapter.class);

        List<File> files = Collections.singletonList(file);
        galleryLayout.addImages(files);
        picassoAdapter.verify(() -> PicassoAdapter.intoView(eq(file),
                eq(250),
                eq(250),
                any(ImageView.class)));
    }
}