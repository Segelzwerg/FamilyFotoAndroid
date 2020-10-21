package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;
import java.io.IOException;
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
        File imagesDirectory = new File(path);
        if (!imagesDirectory.isDirectory()) {
            String message = String.format("Directory not found %s", imagesDirectory.toString());
            throw new IOException(message);
        }

        OnlyImagesFilter onlyImagesFilter = new OnlyImagesFilter();
        File[] files = imagesDirectory.listFiles(onlyImagesFilter);

        if (files == null) {
            String message = String.format("Directory: %s is empty.",
                    imagesDirectory.getAbsolutePath());
            throw new IOException(message);
        }
        return Arrays.asList(files);
    }
}
