package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.FamilyFotoServerService;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;

/**
 * Displays the login form and handles responses visually to login requests.
 */
@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    /**
     * Handles request to the family foto server.
     */
    @Inject
    FamilyFotoServerService server;

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


    /**
     * Requests login to family foto server.
     * @param view the view object that was clicked.
     */
    public void requestLogin(View view) throws IOException {
        EditText editTextUsername = findViewById(R.id.username);
        EditText editTextPassword = findViewById(R.id.password);
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        LoginCredentials loginCredentials = new LoginCredentials(username, password);

        Call<AuthToken> login = server.login(loginCredentials);
        login.enqueue(new LoginCallBack<>(getApplicationContext()));
     }

}
