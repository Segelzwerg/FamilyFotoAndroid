package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.ui.MainActivity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class GalleryLayoutTest {

    IntentsTestRule rule = new IntentsTestRule<>(MainActivity.class);

    @BeforeEach
    public void setUp() {
        rule.launchActivity(new Intent());
    }

    @AfterEach
    public void tearDown() {
        Intents.release();
    }

    /**
     * Tests if the gallery view has the text view and two images which are provided by the AVD.
     */
    @Test
    public void testImagesAreShown() {
        onView(withId(R.id.gallery)).check(matches(hasChildCount(3)));
    }
}