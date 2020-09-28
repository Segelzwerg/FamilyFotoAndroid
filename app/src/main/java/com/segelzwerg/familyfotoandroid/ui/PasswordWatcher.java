package com.segelzwerg.familyfotoandroid.ui;

import android.text.Editable;
import android.text.TextWatcher;

import lombok.Getter;
/**
 * Watches the password field and validates it.
 */
@Getter
public class PasswordWatcher implements TextWatcher {
    /**
     * The minimum required password length.
     */
    public static final int MIN_PASSWORD_LENGTH = 8;
    /**
     * Flag if the typed in password is valid.
     */
    private boolean valid;

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
        valid = password.length() > MIN_PASSWORD_LENGTH;
    }
}
