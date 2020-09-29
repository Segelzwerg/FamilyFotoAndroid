package com.segelzwerg.familyfotoandroid.ui;

import androidx.test.core.app.ActivityScenario;

import com.segelzwerg.familyfotoandroid.R;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import de.mannodermaus.junit5.ActivityScenarioExtension;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static de.mannodermaus.junit5.ActivityScenarioExtension.launch;

public class TestLoginActivity {
    @RegisterExtension
    public ActivityScenarioExtension<LoginActivity> scenarioExtension = launch(LoginActivity.class);

    @Test
    public void testButtonEnabled() {
        ActivityScenario<LoginActivity> scenario = scenarioExtension.getScenario();
        onView(withId(R.id.username)).perform(typeText("marcel"),
                closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("12345678"),
                closeSoftKeyboard());
        onView(withId(R.id.login)).check(matches(isEnabled()));
    }
}
