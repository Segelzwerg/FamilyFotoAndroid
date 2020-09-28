package com.segelzwerg.familyfotoandroid.ui;

import android.text.Editable;
import android.text.TextWatcher;

import lombok.Getter;

/**
 * Watches the username field and validates it.
 */
@Getter
public class UsernameWatcher implements TextWatcher {
    /**
     * Flag if the typed in username is valid.
     */
    private boolean valid;
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * Validates the username after the user typed it in.
     * @param s the editable text which will be watch.
     */
    @Override
    public void afterTextChanged(Editable s) {
        String username = s.toString();
        valid = username.length() > 0;
    }
}
