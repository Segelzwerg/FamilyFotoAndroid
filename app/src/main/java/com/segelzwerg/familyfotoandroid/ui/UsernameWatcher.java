package com.segelzwerg.familyfotoandroid.ui;

import android.text.Editable;

import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import lombok.Getter;
import lombok.NonNull;

/**
 * Watches the username field and validates it.
 */
@Getter
public class UsernameWatcher extends RequiredFieldWatcher {
    /**
     * Constructor.
     * @param button which the field will enabled upon validation.
     */
    public UsernameWatcher(@NonNull LoginButton button) {
        super(button);
        button.addRequiredField(this);
    }

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
        boolean valid = username.length() > 0;
        sendState(valid);
    }
}
