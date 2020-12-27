package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;
import java.io.FilenameFilter;

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
    static File[] getFiles(File directory, FilenameFilter filter) {
        return directory.listFiles(filter);
    }
}
