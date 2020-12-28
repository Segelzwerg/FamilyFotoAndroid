package com.segelzwerg.familyfotoandroid.imageservice.utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Retrieves meta information of an image.
 */
public class ImageProperties {
    /**
     * Gets the mime type of a file.
     *
     * @param url to the file.
     * @return String of the mime type.
     * @throws IOException if something goes wrong reading the file.
     */
    public static String getMimeType(String url) throws IOException {
        File file = getFile(url);
        URLConnection connection = getConnection(file);
        return getContentType(connection);
    }

    private static String getContentType(URLConnection connection) {
        return connection.getContentType();
    }

    private static File getFile(String url) {
        Path path = getPath(url);
        return toFile(path);
    }

    private static File toFile(Path path) {
        return path.toFile();
    }

    private static Path getPath(String url) {
        return Paths.get(url);
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
