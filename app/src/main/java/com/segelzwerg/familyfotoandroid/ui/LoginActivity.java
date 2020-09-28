package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

/**
 * Displays the login form and handles responses visually to login requests.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextUsername = findViewById(R.id.username);
        EditText editTextPassword = findViewById(R.id.password);
        LoginButton submitButton = findViewById(R.id.login);

        UsernameWatcher usernameWatcher = new UsernameWatcher(submitButton);
        PasswordWatcher passwordWatcher = new PasswordWatcher(submitButton);

        editTextUsername.addTextChangedListener(usernameWatcher);
        editTextPassword.addTextChangedListener(passwordWatcher);
    }


}
