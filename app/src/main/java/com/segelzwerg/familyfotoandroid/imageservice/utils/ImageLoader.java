package com.segelzwerg.familyfotoandroid.imageservice.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Loads images from the internal storage.
 */
public class ImageLoader {
    /**
     * Loads the images and returns them as list.
     * @param path from where the images should be loaded.
     * @throws IOException is thrown if some images could not be read.
     * @return {@link List} images as {@link File} in a list.
     */
    public static List<File> loadImages(String path) throws IOException {
        File imagesDirectory = new File(path);
        if (!imagesDirectory.isDirectory()) {
            String message = String.format("Directory not found %s", imagesDirectory.toString());
            Log.e("IO", message);
            throw new IOException(message);
        }
        File[] files = Objects.requireNonNull(imagesDirectory.listFiles());
        return Arrays.asList(files);
    }
}
