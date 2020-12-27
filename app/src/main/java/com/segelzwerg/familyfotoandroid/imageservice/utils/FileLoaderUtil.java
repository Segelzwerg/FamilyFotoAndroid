package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;

public class FileLoaderUtil {
    static File[] getFiles(File imagesDirectory) {
        OnlyImagesFilter onlyImagesFilter = new OnlyImagesFilter();
        return imagesDirectory.listFiles(onlyImagesFilter);
    }
}
