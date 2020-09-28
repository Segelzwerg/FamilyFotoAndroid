package com.segelzwerg.familyfotoandroid.ui;

import android.text.TextWatcher;

import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Watches required fields in a form.
 */
@RequiredArgsConstructor
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
    private LoginButton button;

    /**
     * Sends the button the current state.
     * @param valid the validation state of the field content
     */
    void sendState(boolean valid) {
        this.valid = valid;
        button.checkState();
    }
}
