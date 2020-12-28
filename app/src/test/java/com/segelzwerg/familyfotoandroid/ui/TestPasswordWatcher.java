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

public class TestPasswordWatcher {

    private LoginButton loginButton;
    private Editable validEditable;
    private Editable invalidEditable;
    private PasswordWatcher usernameWatcher;

    @BeforeEach
    public void setUp() {
        loginButton = mock(LoginButton.class);
        validEditable = mock(Editable.class);
        invalidEditable = mock(Editable.class);

        usernameWatcher = new PasswordWatcher(loginButton);

        when(validEditable.toString()).thenReturn("abc1234#");
        when(invalidEditable.toString()).thenReturn("");
    }

    @Test
    public void testAddRequiredField() {
        verify(loginButton, times(1)).addRequiredField(usernameWatcher);

    }

    @Test
    public void testAfterTextChanged() {
        usernameWatcher.afterTextChanged(validEditable);
        verify(loginButton, times(1)).checkState();
    }

    @Test
    public void testIsValidIsDefaultFalse() {
        assertThat(usernameWatcher.isValid()).isFalse();
    }

    @Test
    public void testIsValidSwitchTrue() {
        usernameWatcher.afterTextChanged(validEditable);
        assertThat(usernameWatcher.isValid()).isTrue();
    }

    @Test
    public void testIsInvalid() {
        usernameWatcher.afterTextChanged(invalidEditable);
        assertThat(usernameWatcher.isValid()).isFalse();
    }

    @Test
    public void testIsValidSwitchBack() {
        usernameWatcher.afterTextChanged(validEditable);
        usernameWatcher.afterTextChanged(invalidEditable);
        assertThat(usernameWatcher.isValid()).isFalse();
    }
}
