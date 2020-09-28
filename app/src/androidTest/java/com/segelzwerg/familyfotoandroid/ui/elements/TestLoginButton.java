package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.segelzwerg.familyfotoandroid.ui.RequiredFieldWatcher;
import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestLoginButton {

    private Context context;
    private LoginButton loginButton;
    private RequiredFieldWatcher requiredFieldWatcher;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        loginButton = new LoginButton(context);

        requiredFieldWatcher = Mockito.mock(RequiredFieldWatcher.class);
        loginButton.addRequiredField(requiredFieldWatcher);

    }

    @Test
    public void testLoginButton() {
        Mockito.when(requiredFieldWatcher.isValid()).thenReturn(true);

        loginButton.checkState();
        AssertionsForClassTypes.assertThat(loginButton.isEnabled()).isTrue();
    }

    @Test
    public void testLoginButtonNotValid() {
        Mockito.when(requiredFieldWatcher.isValid()).thenReturn(false);

        loginButton.checkState();
        AssertionsForClassTypes.assertThat(loginButton.isEnabled()).isFalse();
    }
}
