package com.segelzwerg.familyfotoandroid.imageservice;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.segelzwerg.familyfotoandroid.familyfotoservice.UploaderQueue;
import com.segelzwerg.familyfotoandroid.ui.activities.MainActivity;
import com.segelzwerg.familyfotoandroid.utils.ActivityUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.testing.HiltAndroidTest;

import static org.assertj.core.api.Assertions.assertThat;

@HiltAndroidTest
public class ImageScraperTest {
    IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    @AfterEach
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void test_upload_queue() {
        rule.launchActivity(new Intent());
        MainActivity mainActivity = (MainActivity) ActivityUtils.getActivityInstance();
        UploaderQueue uploaderQueue = mainActivity.getUploaderQueue();
        List<String> expectedQueue = new ArrayList<>(Arrays.asList(
                "/storage/emulated/0/DCIM/Camera/IMG-20190717-WA0004.jpg",
                "/storage/emulated/0/DCIM/Camera/IMG-20190717-WA0006.jpg"));
        assertThat(uploaderQueue).hasFieldOrPropertyWithValue("filesQueued", expectedQueue);
    }
}