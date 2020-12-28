package com.segelzwerg.familyfotoandroid.ui;

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
    public static final int MIN_PASSWORD_LENGTH = 4;

    /**
     * Constructor.
     * @param button which the field will enabled upon validation.
     */
    public PasswordWatcher(@NonNull LoginButton button) {
        super(button);
    }

    @Override
    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        //not implemented
    }

    @Override
    public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        //not implemented
    }

    /**
     * Validates the password after the user typed it in.
     * @param input to validate
     */
    @Override
    protected void validateInput(String input) {
        boolean valid = input.length() >= MIN_PASSWORD_LENGTH;
        sendState(valid);
    }
}
