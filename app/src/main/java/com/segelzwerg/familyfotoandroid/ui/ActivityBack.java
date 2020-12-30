package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.ui.activities.MainActivity;
import com.segelzwerg.familyfotoandroid.ui.utils.ActivityUtils;

/**
 * Abstract class that has a back button in the top action bar.
 */
public abstract class ActivityBack extends AppCompatActivity {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.enableBackButton(getSupportActionBar());
    }

    /**
     * Goes back to the previous activity unless it is the first one called. Then it goes to the
     * main activity.
     *
     * @param item the menu item being pressed. In this case the back button.
     * @return if it was successful.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            ActivityUtils.gotoActivity(this, MainActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
}
