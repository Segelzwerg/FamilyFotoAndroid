package com.segelzwerg.familyfotoandroid.ui;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.FamilyFotoServerService;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.LoginCredentials;

import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestLoginActivity {
    @RegisterExtension
    public ActivityScenarioExtension<LoginActivity> scenarioExtension = launch(LoginActivity.class);

    private FamilyFotoServerService familyFotoServerService;

    @BeforeEach
    public void setUp() {
        familyFotoServerService = mock(FamilyFotoServerService.class);
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
        verify(familyFotoServerService).login(any(LoginCredentials.class));
    }
}
