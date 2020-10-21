package com.segelzwerg.familyfotoandroid.imageservice.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.segelzwerg.familyfotoandroid.imageservice.utils.ImageLoaderUtil.loadImages;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class ImageLoaderUtilTest {
    @Test
    public void loadImagesTest() throws IOException {
        File directory = new File("src/test/resources/");
        List<File> files = loadImages(directory.getAbsolutePath());
        File testImage = new File("src/test/resources/" + "test-image.jpg");
        assertThat(files).hasSize(1);
        assertThat(files.get(0)).hasSameBinaryContentAs(testImage);
    }

    @Test
    public void testPathIsNotDirectory() {
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> ImageLoaderUtil.loadImages("text.txt"));
    }

    @Test
    public void testPathIsEmpty() {
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> ImageLoaderUtil.loadImages("src/test/resources/EmptyDir"));
    }
}