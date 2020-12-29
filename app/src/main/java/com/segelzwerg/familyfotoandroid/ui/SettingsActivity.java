package com.segelzwerg.familyfotoandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.ui.elements.SettingsFragment;

/**
 * UI for dealing with all sorts of settings.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }

    /**
     * Saves the settings.
     *
     * @param view from where the method is called.
     */
    public void saveSettings(View view) {
        String hostname = editHostname.getString();
        int port = editPort.getInt();
        BaseUrlModule.setHost(hostname);
        BaseUrlModule.setPort(port);
    }
}
