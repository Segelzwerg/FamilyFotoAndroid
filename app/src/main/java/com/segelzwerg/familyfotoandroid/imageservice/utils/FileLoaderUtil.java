package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Loads files a directory.
 */
public class FileLoaderUtil {
    /**
     * Loads file given a filter.
     *
     * @param directory from where the files should be loaded
     * @param filter    loads only files that match the criteria
     * @return a array of {@link File}
     */
    public static File[] getFiles(File directory, FilenameFilter filter) {
        return directory.listFiles(filter);
    }

    /**
     * Retrieves one file from the device.
     *
     * @param path to the file
     * @return {@link File}
     */
    public static File getFile(String path) {
        return pathToFile(Paths.get(path));
    }

    private static File pathToFile(Path path) {
        return path.toFile();
    }
}
