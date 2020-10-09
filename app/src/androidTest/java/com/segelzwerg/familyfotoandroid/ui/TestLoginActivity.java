package com.segelzwerg.familyfotoandroid.ui;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.segelzwerg.familyfotoandroid.R;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import dagger.hilt.android.testing.HiltAndroidTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.segelzwerg.familyfotoandroid.familyfotoservice.BaseUrlModule.MOCK_SERVER_PORT;

@HiltAndroidTest
public class TestLoginActivity {
    @Rule
    IntentsTestRule<LoginActivity> rule = new IntentsTestRule<>(LoginActivity.class);
    private MockWebServer mockWebServer;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(MOCK_SERVER_PORT);
        rule.launchActivity(new Intent());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Intents.release();
        mockWebServer.shutdown();
    }

    @Test
    public void testButtonEnabled() {
        onView(withId(R.id.username)).perform(typeText("marcel"),
                closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"),
                closeSoftKeyboard());
        onView(withId(R.id.login)).check(matches(isEnabled()));
    }

    @Test
    public void testLoginSuccess() throws IOException {
        MockResponse response = new MockResponse()
            .setResponseCode(HttpsURLConnection.HTTP_OK)
            .setBody("{token:token}");
        mockWebServer.enqueue(response);
        onView(withId(R.id.username)).perform(typeText("marcel"),
                closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"),
                closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }
}
