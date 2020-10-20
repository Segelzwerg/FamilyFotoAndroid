package com.segelzwerg.familyfotoandroid.imageservice;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.segelzwerg.familyfotoandroid.imageservice.utils.ImageLoaderUtil.loadImages;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageLoaderUtilTest {

    @Test
    public void loadImagesTest() throws IOException {
        File directory = new File("src/test/resources/");
        List<File> files = loadImages(directory.getAbsolutePath());
        File testImage = new File("src/test/resources/" + "test-image.jpg");
        assertThat(files).hasSize(1);
        assertThat(files.get(0)).hasSameBinaryContentAs(testImage);
    }
}