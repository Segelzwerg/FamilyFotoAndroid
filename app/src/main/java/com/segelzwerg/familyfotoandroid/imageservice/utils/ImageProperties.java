package com.segelzwerg.familyfotoandroid.imageservice.utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Retrieves meta information of an image.
 */
public class ImageProperties {
    /**
     * Gets the mime type of a file.
     *
     * @param file to the file.
     * @return String of the mime type.
     * @throws IOException if something goes wrong reading the file.
     */
    public static String getMimeType(File file) throws IOException {
        URLConnection connection = getConnection(file);
        return getContentType(connection);
    }

    private static String getContentType(URLConnection connection) {
        return connection.getContentType();
    }

    private static URLConnection getConnection(File file) throws IOException {
        URL url = getUrl(file);
        return openConnection(url);
    }

    private static URLConnection openConnection(URL url) throws IOException {
        return url.openConnection();
    }

    @NotNull
    private static URL getUrl(File file) throws MalformedURLException {
        URI uri = getUri(file);
        return toUrl(uri);
    }

    private static URL toUrl(URI uri) throws MalformedURLException {
        return uri.toURL();
    }

    @NotNull
    private static URI getUri(File file) {
        return file.toURI();
    }


}
