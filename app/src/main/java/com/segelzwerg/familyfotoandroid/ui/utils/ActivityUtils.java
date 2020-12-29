package com.segelzwerg.familyfotoandroid.ui.utils;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Utility class for handy activity tools.
 */
public class ActivityUtils {
    /**
     * Opens a new activity and closes the old one.
     *
     * @param activity      current activity
     * @param activityClass of the new activity
     */
    public static void gotoActivity(AppCompatActivity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass);
        activity.startActivity(intent);
        activity.finish();
    }
}
