package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familyfotoservice.BaseUrlModule;
import com.segelzwerg.familyfotoandroid.ui.elements.IntEditText;
import com.segelzwerg.familyfotoandroid.ui.elements.StringEditText;

/**
 * UI for dealing with all sorts of settings.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * Text input for hostname.
     */
    private StringEditText editHostname;
    /**
     * Text input for port number.
     */
    private IntEditText editPort;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editHostname = findViewById(R.id.hostnameInput);
        editPort = findViewById(R.id.portInput);
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
