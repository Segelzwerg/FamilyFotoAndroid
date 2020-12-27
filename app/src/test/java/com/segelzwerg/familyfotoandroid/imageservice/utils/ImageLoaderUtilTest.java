package com.segelzwerg.familyfotoandroid.imageservice.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.segelzwerg.familyfotoandroid.imageservice.utils.ImageLoaderUtil.loadImages;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

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
    public void testPathIsEmpty() {
        Path emptyDir = Paths.get(RESOURCE_PATH.toString(), EMPTY_DIR);
        try (MockedStatic<FileLoaderUtil> fileLoader = mockStatic(FileLoaderUtil.class)) {
            fileLoader.when(() -> FileLoaderUtil.getFiles(emptyDir.toFile(), new OnlyImagesFilter())).thenReturn(new File[]{});
            assertThatExceptionOfType(IOException.class)
                    .isThrownBy(() -> ImageLoaderUtil.loadImages(emptyDir.toString()))
                    .withMessageContaining("is empty.");
        }
    }

    @Test
    public void testNullFile() throws IOException {
        try (MockedStatic<FileLoaderUtil> fileLoader = mockStatic(FileLoaderUtil.class)) {
            fileLoader.when(() -> FileLoaderUtil.getFiles(any(), new OnlyImagesFilter())).thenReturn(null);
            String absolutePath = RESOURCE_PATH.toAbsolutePath() + "/emptyDir";
            List<File> files = loadImages(absolutePath);
            assertThat(files).hasSize(0);
        }
    }
}