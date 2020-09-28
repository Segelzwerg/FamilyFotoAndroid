package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.segelzwerg.familyfotoandroid.ui.RequiredFieldWatcher;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestLoginButton {

    private LoginButton loginButton;
    private RequiredFieldWatcher requiredFieldWatcher;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        loginButton = new LoginButton(context);

        requiredFieldWatcher = mock(RequiredFieldWatcher.class);
        loginButton.addRequiredField(requiredFieldWatcher);

    }

    @Test
    public void testLoginButton() {
        when(requiredFieldWatcher.isValid()).thenReturn(true);

        loginButton.checkState();
        assertThat(loginButton.isEnabled()).isTrue();
    }

    @Test
    public void testLoginButtonNotValid() {
        when(requiredFieldWatcher.isValid()).thenReturn(false);

        loginButton.checkState();
        assertThat(loginButton.isEnabled()).isFalse();
    }
}
