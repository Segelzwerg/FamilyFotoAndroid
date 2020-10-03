package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The main activity of this app.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView welcomeTextView = new TextView(this);

        String welcomeText = "Welcome to Family Foto!";
        welcomeTextView.setText(welcomeText);
        setContentView(welcomeTextView);
    }
}
