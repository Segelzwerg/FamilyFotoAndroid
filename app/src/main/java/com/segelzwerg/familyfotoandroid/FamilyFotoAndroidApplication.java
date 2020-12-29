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
     * Key for the hostname.
     */
    public static final String HOSTNAME_KEY = "hostname";
    /**
     * Key for the host port.
     */
    public static final String HOST_PORT_KEY = "host_port";
    /**
     * Is the server ip running the app in an emulator.
     */
    @SuppressWarnings("PMD.AvoidUsingHardCodedIP")
    public static final String EMULATOR_HOST = "10.0.2.2";

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(this);
        String hostname = sharedPreferences.getString(HOSTNAME_KEY, EMULATOR_HOST);
        int port = Integer.parseInt(sharedPreferences.getString(HOST_PORT_KEY,
                String.valueOf(FLASK_DEFAULT_PORT)));
        BaseUrlModule.setHost(hostname);
        BaseUrlModule.setPort(port);
    }
}
