package com.segelzwerg.familyfotoandroid.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.ui.ActivityBack;
import com.segelzwerg.familyfotoandroid.ui.elements.SettingsFragment;

/**
 * UI for dealing with all sorts of settings.
 */
public class SettingsActivity extends ActivityBack {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }

}
