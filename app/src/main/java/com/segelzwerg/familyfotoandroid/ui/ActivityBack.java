package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.ui.utils.ActivityUtils;

/**
 * Abstract class that has a back button in the top action bar.
 */
public abstract class ActivityBack extends AppCompatActivity {
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.enableBackButton(getSupportActionBar());
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            ActivityUtils.gotoActivity(this, MainActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
}
