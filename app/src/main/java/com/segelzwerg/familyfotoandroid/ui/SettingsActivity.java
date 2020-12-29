package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.ui.elements.SettingsFragment;
import com.segelzwerg.familyfotoandroid.ui.utils.ActivityUtils;

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
        enableBackButton(getSupportActionBar());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            ActivityUtils.gotoActivity(this, MainActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableBackButton(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
