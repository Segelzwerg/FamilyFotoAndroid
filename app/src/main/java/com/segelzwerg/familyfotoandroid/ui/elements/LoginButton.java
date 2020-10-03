package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.segelzwerg.familyfotoandroid.ui.RequiredFieldWatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Extends the AppCompatButton Class for custom activation with field content validation.
 */
public class LoginButton extends AppCompatButton {
    /**
     * List of input fields which must be valid before button activation.
     */
    private transient List<RequiredFieldWatcher> requiredFields = new ArrayList<>();

    /**
     * Constructor.
     * @param context the context where the button is placed.
     */
    public LoginButton(@NonNull Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context the context where the button is placed.
     * @param attrs The attributes of the XML Button tag being used to inflate the view.
     */
    public LoginButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context the context where the button is placed.
     * @param attrs The attributes of the XML Button tag being used to inflate the view.
     * @param defStyleAttr The resource identifier of an attribute in the current theme whose value
     *                     is the the resource id of a style. The specified style?s attribute values
     *                     serve as default values for the button. Set this parameter to 0 to avoid
     *                     use of default values.
     */
    public LoginButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Adds an input field to the list of required fields.
     * @param newField input field to be added.
     */
    public void addRequiredField(RequiredFieldWatcher newField) {
        requiredFields.add(newField);
    }

    /**
     * Checks if all required fields are valid.
     */
    public void checkState() {
        boolean enabled = requiredFields.stream()
                .allMatch(RequiredFieldWatcher::isValid);
        setEnabled(enabled);
    }
}
