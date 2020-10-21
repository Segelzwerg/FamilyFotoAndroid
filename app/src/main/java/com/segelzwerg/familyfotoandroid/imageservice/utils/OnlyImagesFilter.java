package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

/**
 * Only allows images files in directory.
 */
public class OnlyImagesFilter implements FilenameFilter {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accept(File dir, String name) {
        return isJpg(name.toLowerCase(Locale.getDefault()));
    }

    private boolean isJpg(String name) {
        return name.endsWith(".jpg");
    }
}
