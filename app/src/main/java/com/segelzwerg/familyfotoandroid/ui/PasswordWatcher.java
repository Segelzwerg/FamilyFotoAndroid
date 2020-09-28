package com.segelzwerg.familyfotoandroid.ui;

import android.text.Editable;

import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import lombok.Getter;
import lombok.NonNull;

/**
 * Watches the password field and validates it.
 */
@Getter
public class PasswordWatcher extends RequiredFieldWatcher {
    /**
     * The minimum required password length.
     */
    public static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * Constructor.
     * @param button which the field will enabled upon validation.
     */
    public PasswordWatcher(@NonNull LoginButton button) {
        super(button);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * Validates the password after the user typed it in.
     * @param s the editable text which will be watch.
     */
    @Override
    public void afterTextChanged(Editable s) {
        String password = s.toString();
        boolean valid = password.length() > MIN_PASSWORD_LENGTH;
        sendState(valid);
    }
}
