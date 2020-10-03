package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

/**
 * Field of a form that is required before a request can be sent.
 */
public class RequiredField extends AppCompatEditText {
    /**
     * Constructor.
     * @param context where the field is initialized
     */
    public RequiredField(@NonNull Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context where the field is initialized
     * @param attrs of the field
     */
    public RequiredField(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * Constructor.
     * @param context where the field is initialized
     * @param attrs of the field
     * @param defStyleAttr style of the field
     */
    public RequiredField(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Returns the fields content as string.
     * @return input as string
     */
    public String getString() {
        return Objects.requireNonNull(getText()).toString();
    }
}
