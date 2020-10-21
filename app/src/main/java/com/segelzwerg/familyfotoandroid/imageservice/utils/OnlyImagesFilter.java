package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Only allows images files in directory.
 */
public class OnlyImagesFilter implements FilenameFilter {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".jpg");
    }
}
