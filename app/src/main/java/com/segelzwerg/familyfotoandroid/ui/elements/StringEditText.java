package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class StringEditText extends AppCompatEditText {
    public StringEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StringEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StringEditText(Context context) {
        super(context);
    }

    /**
     * Returns the fields content as string.
     *
     * @return input as string
     */
    public String getString() {
        return extractString(Objects.requireNonNull(getText()));
    }

    /**
     * Extracts the editable content as string.
     *
     * @param editable from which the strong should be extracted
     * @return the content as string
     */
    private String extractString(Editable editable) {
        return editable.toString();
    }
}