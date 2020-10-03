package com.segelzwerg.familyfotoandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.FamilyFotoServerService;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;

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
    // Dagger can't inject in private fields.
    @SuppressWarnings({"checkstyle:VisibilityModifier", "PMD.DefaultPackage"})
    @Inject
    transient FamilyFotoServerService server;
    /**
     * Text field for username input.
     */
    private transient EditText editTextUsername;
    /**
     * Text field for password input.
     */
    private transient EditText editTextPassword;
    /**
     * Button for submitting login request.
     */
    private transient LoginButton submitButton;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        submitButton = findViewById(R.id.login);

        UsernameWatcher usernameWatcher = new UsernameWatcher(submitButton);
        PasswordWatcher passwordWatcher = new PasswordWatcher(submitButton);

        editTextUsername.addTextChangedListener(usernameWatcher);
        editTextPassword.addTextChangedListener(passwordWatcher);
    }


    /**
     * Requests login to family foto server.
     * @param view the view object that was clicked.
     */
    public void requestLogin(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        LoginCredentials loginCredentials = new LoginCredentials(username, password);

        Call<AuthToken> login = server.login(loginCredentials);
        login.enqueue(new LoginCallBack<>(getApplicationContext()));
     }

}
