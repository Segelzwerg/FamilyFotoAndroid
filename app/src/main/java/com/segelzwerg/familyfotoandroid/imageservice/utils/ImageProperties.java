package com.segelzwerg.familyfotoandroid.imageservice.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Paths;

public class ImageProperties {
    public static String getMimeType(String url) throws IOException {
        File file = Paths.get(url).toFile();
        URLConnection connection = file.toURI().toURL().openConnection();
        return connection.getContentType();
    }
}
