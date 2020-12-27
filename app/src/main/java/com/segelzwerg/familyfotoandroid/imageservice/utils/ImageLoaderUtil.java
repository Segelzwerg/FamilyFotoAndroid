package com.segelzwerg.familyfotoandroid.imageservice.utils;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Loads images from the internal storage.
 */
public class ImageLoaderUtil {
    /**
     * Loads the images and returns them as list.
     * @param path from where the images should be loaded.
     * @throws IOException is thrown if some images could not be read.
     * @return {@link List} images as {@link File} in a list.
     */
    public static List<File> loadImages(String path) throws IOException {
        File imagesDirectory = getImageDirectory(path);

        File[] files = FileLoaderUtil.getFiles(imagesDirectory, new OnlyImagesFilter());

        if (files == null) {
            String message = "Probably permission for external storage usage not granted.";
            Log.e("ERROR", message);
            return new ArrayList<>();
        } else if (files.length == 0) {
            String message = String.format("Directory: %s is empty.",
                    imagesDirectory.getAbsolutePath());
            throw new IOException(message);
        }
        return Arrays.asList(files);
    }

    @NotNull
    private static File getImageDirectory(String path) throws IOException {
        File imagesDirectory = new File(path);
        if (!imagesDirectory.isDirectory()) {
            String message = String.format("Directory not found %s", imagesDirectory.toString());
            throw new IOException(message);
        }
        return imagesDirectory;
    }
}
