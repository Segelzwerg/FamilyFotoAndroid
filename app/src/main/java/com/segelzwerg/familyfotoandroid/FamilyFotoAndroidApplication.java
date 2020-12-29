package com.segelzwerg.familyfotoandroid;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.segelzwerg.familyfotoandroid.familyfotoservice.BaseUrlModule;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Application as an entry point for hilt.
 */
@HiltAndroidApp
public class FamilyFotoAndroidApplication extends Application {

    /**
     * Default port used by flask.
     */
    public static final int FLASK_DEFAULT_PORT = 5000;

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String hostname = sharedPreferences.getString("hostname", "10.0.2.2");
        int port = Integer.parseInt(sharedPreferences.getString("host_port",
                String.valueOf(FLASK_DEFAULT_PORT)));
        BaseUrlModule.setHost(hostname);
        BaseUrlModule.setPort(port);
    }
}
