package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class RequiredField extends AppCompatEditText {
    public RequiredField(@NonNull Context context) {
        super(context);
    }

    public RequiredField(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

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
