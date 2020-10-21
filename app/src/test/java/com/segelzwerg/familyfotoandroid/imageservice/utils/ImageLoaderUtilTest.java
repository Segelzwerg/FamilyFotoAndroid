package com.segelzwerg.familyfotoandroid.imageservice.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.segelzwerg.familyfotoandroid.imageservice.utils.ImageLoaderUtil.loadImages;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImageLoaderUtilTest {

    public static final Path RESOURCE_PATH = Paths.get("src/test/resources/");
    public static final String TEST_IMAGE_JPG = "test-image.jpg";
    public static final String TEXT_TXT = "text.txt";
    public static final String EMPTY_DIR = "EmptyDir";

    @Test
    public void loadImagesTest() throws IOException {
        String absolutePath = String.valueOf(RESOURCE_PATH.toAbsolutePath());
        List<File> files = loadImages(absolutePath);
        Path imagePath = Paths.get(RESOURCE_PATH.toString(), TEST_IMAGE_JPG);
        File testImage = imagePath.toFile();
        assertThat(files).hasSize(1);
        assertThat(files.get(0)).hasSameBinaryContentAs(testImage);
    }

    @Test
    public void testPathIsNotDirectory() {
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> ImageLoaderUtil.loadImages(TEXT_TXT))
                .withMessageContaining("Directory not found");
    }

    @Test
    public void testPathIsEmpty() throws IOException {
        Path emptyDir = Paths.get(RESOURCE_PATH.toString(), EMPTY_DIR);
        if (Files.exists(emptyDir)) {
            Files.delete(emptyDir);
        }
        Files.createDirectory(emptyDir);
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> ImageLoaderUtil.loadImages(emptyDir.toString()))
                .withMessageContaining("is empty.");
        Files.delete(emptyDir);
    }
}