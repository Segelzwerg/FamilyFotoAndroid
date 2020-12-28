package com.segelzwerg.familyfotoandroid.imageservice.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.segelzwerg.familyfotoandroid.imageservice.utils.ImageProperties.getMimeType;
import static org.assertj.core.api.Assertions.assertThat;

public class ImagePropertiesTest {
    public static final String DIR_PATH = "src/test/resources/";
    private static final String pathToFile = String.format("%stest-image.jpg", DIR_PATH);

    @Test
    public void testMimeType() throws IOException {
        Path path = Paths.get(pathToFile);
        String url = path.toAbsolutePath().toString();
        assertThat(getMimeType(url)).isEqualTo("image/jpeg");
    }
}