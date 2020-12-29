package com.segelzwerg.familyfotoandroid.ui;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.google.gson.Gson;
import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthTokenResponse;
import com.segelzwerg.familyfotoandroid.familyfotoservice.BaseUrlModule;
import com.segelzwerg.familyfotoandroid.utils.ActivityUtils;

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
import static org.assertj.core.api.Assertions.assertThat;

@HiltAndroidTest
public class TestLoginActivity {
    private static final int SERVER_PORT = 5000;
    @Rule
    IntentsTestRule<LoginActivity> rule = new IntentsTestRule<>(LoginActivity.class);
    private MockWebServer mockWebServer;


    @BeforeEach
    public void setUp() throws IOException {
        BaseUrlModule.setHost("localhost");
        mockWebServer = new MockWebServer();
        mockWebServer.start(SERVER_PORT);
        rule.launchActivity(new Intent());
        AuthTokenResponse authTokenResponse = new AuthTokenResponse(new AuthToken("token"),
                1);

        Gson gson = new Gson();
        MockResponse response = new MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(gson.toJson(authTokenResponse));
        mockWebServer.enqueue(response);
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
    public void testLoginSuccess() {
        onView(withId(R.id.username)).perform(typeText("marcel"),
                closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"),
                closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void testTokenSaved() throws Exception {
        onView(withId(R.id.username)).perform(typeText("marcel"),
                closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"),
                closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        MainActivity mainActivity = (MainActivity) ActivityUtils.getActivityInstance();
        AuthToken authToken = mainActivity.getAuthToken();
        assertThat(authToken).usingRecursiveComparison().isNotNull();
    }
}
