package com.segelzwerg.familyfotoandroid.ui;

import android.text.Editable;

import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestUsernameWatcher {

    private LoginButton loginButton;
    private Editable validEditable;
    private Editable invalidEditable;

    @BeforeEach
    public void setUp() {
        loginButton = mock(LoginButton.class);
        validEditable = mock(Editable.class);
        invalidEditable = mock(Editable.class);

        when(validEditable.toString()).thenReturn("abc");
        when(invalidEditable.toString()).thenReturn("");
    }

    @Test
    public void testAddRequiredField() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        verify(loginButton, times(1)).addRequiredField(usernameWatcher);

    }

    @Test
    public void testAfterTextChanged() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        usernameWatcher.afterTextChanged(validEditable);
        verify(loginButton, times(1)).checkState();
    }

    @Test
    public void testIsValidIsDefaultFalse() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        assertThat(usernameWatcher.isValid()).isFalse();
    }

    @Test
    public void testIsValidSwitchTrue() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        usernameWatcher.afterTextChanged(validEditable);
        assertThat(usernameWatcher.isValid()).isTrue();
    }

    @Test
    public void testIsInvalid() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        usernameWatcher.afterTextChanged(invalidEditable);
        assertThat(usernameWatcher.isValid()).isFalse();
    }

    @Test
    public void testIsValidSwitchBack() {
        UsernameWatcher usernameWatcher = new UsernameWatcher(loginButton);
        usernameWatcher.afterTextChanged(validEditable);
        usernameWatcher.afterTextChanged(invalidEditable);
        assertThat(usernameWatcher.isValid()).isFalse();
    }
}
