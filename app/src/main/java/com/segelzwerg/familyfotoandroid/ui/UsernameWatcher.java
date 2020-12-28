package com.segelzwerg.familyfotoandroid.ui;

import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import lombok.Getter;

/**
 * Watches the username field and validates it.
 */
@Getter
public class UsernameWatcher extends RequiredFieldWatcher {


    /**
     * Constructor.
     *
     * @param button which the field will enabled upon validation.
     */
    public UsernameWatcher(LoginButton button) {
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
     * Validates the username after the user typed it in.
     * @param input to be validated.
     */
    @Override
    protected void validateInput(String input) {
        boolean valid = input.length() > 0;
        sendState(valid);
    }
}
