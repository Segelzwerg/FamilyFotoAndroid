package com.segelzwerg.familyfotoandroid.ui;

import android.text.TextWatcher;

import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import lombok.Getter;
import lombok.NonNull;

/**
 * Watches required fields in a form.
 */
public abstract class RequiredFieldWatcher implements TextWatcher {
    /**
     * Flag if the typed in field content is valid.
     */
    @Getter
    private boolean valid;
    /**
     * The button which will be activated if field is valid.
     */
    @NonNull
    private final transient LoginButton button;

    /**
     * Constructor.
     * @param button which the field will enabled upon validation.
     */
    public RequiredFieldWatcher(LoginButton button) {
        this.button = button;
        button.addRequiredField(this);
    }

    /**
     * Sends the button the current state.
     * @param valid the validation state of the field content
     */
    protected void sendState(boolean valid) {
        this.valid = valid;
        button.checkState();
    }
}
