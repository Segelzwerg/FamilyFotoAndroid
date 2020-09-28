package com.segelzwerg.familyfotoandroid;

import android.text.Editable;

import com.segelzwerg.familyfotoandroid.ui.UsernameWatcher;
import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestUsernameWatcher {

    private LoginButton loginButton;

    @BeforeEach
    public void setUp() {
        loginButton = mock(LoginButton.class);
    }

    @Test
    public void testAddRequiredField() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        verify(loginButton, times(1)).addRequiredField(usernameWatcher);

    }

    @Test
    public void testAfterTextChanged() {
        Editable editable = mock(Editable.class);
        when(editable.toString()).thenReturn("abc");
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        usernameWatcher.afterTextChanged(editable);
        verify(loginButton, times(1)).checkState();
    }
}
