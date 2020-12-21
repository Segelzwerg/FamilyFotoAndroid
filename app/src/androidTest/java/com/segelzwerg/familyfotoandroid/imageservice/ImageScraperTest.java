package com.segelzwerg.familyfotoandroid.imageservice;

import android.content.Intent;
import android.os.Environment;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.segelzwerg.familyfotoandroid.familyfotoservice.UploaderQueue;
import com.segelzwerg.familyfotoandroid.ui.MainActivity;
import com.segelzwerg.familyfotoandroid.utils.ActivityUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.testing.HiltAndroidTest;

import static org.assertj.core.api.Assertions.assertThat;

@HiltAndroidTest
public class ImageScraperTest {
    IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void test_upload_queue() throws IOException {
        rule.launchActivity(new Intent());
        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
        String pathToFile = sdCardPath + "/DCIM/Camera/";
        File file = new File(pathToFile, "test.jpg");
        FileWriter fileWriter = new FileWriter((file));
        fileWriter.append("test content");
        fileWriter.flush();
        fileWriter.close();
        MainActivity mainActivity = (MainActivity) ActivityUtils.getActivityInstance();
        UploaderQueue uploaderQueue = mainActivity.getUploaderQueue();
        List<String> expectedQueue = new ArrayList<String>(Arrays.asList(
                "/storage/emulated/0/DCIM/Camera/IMG-20190717-WA0004.jpg",
                "/storage/emulated/0/DCIM/Camera/IMG-20190717-WA0006.jpg",
                pathToFile + "test.jpg"));
        assertThat(uploaderQueue).hasFieldOrPropertyWithValue("filesQueued", expectedQueue);
    }
}