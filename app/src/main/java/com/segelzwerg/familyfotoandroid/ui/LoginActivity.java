package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthTokenResponse;
import com.segelzwerg.familyfotoandroid.familyfotoservice.FamilyFotoServerService;
import com.segelzwerg.familyfotoandroid.familyfotoservice.Header;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;
import com.segelzwerg.familyfotoandroid.ui.elements.LoginButton;
import com.segelzwerg.familyfotoandroid.ui.elements.RequiredField;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;

/**
 * Displays the login form and handles responses visually to login requests.
 */
@AndroidEntryPoint
public class LoginActivity extends ActivityBack {
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
    private transient RequiredField editTextUsername;
    /**
     * Text field for password input.
     */
    private transient RequiredField editTextPassword;
    /**
     * Handles request to the Google account API.
     */
    private transient UserManager userManager;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        LoginButton submitButton = findViewById(R.id.login_button);

        UsernameWatcher usernameWatcher = new UsernameWatcher(submitButton);
        PasswordWatcher passwordWatcher = new PasswordWatcher(submitButton);

        editTextUsername.addTextChangedListener(usernameWatcher);
        editTextPassword.addTextChangedListener(passwordWatcher);

        AccountManager accountManager = AccountManager.get(this);
        userManager = new UserManager(accountManager);
    }


    /**
     * Requests login to family foto server.
     *
     * @param view the view object that was clicked.
     */
    public void requestLogin(View view) {
        String username = editTextUsername.getString();
        String password = editTextPassword.getString();
        LoginCredentials loginCredentials = new LoginCredentials(username, password);
        Header header = new Header();
        header.addAuthentication(loginCredentials);
        Call<AuthTokenResponse> login = server.login(header.getHeaders());
        login.enqueue(new LoginCallBack<>(getApplicationContext(), userManager, loginCredentials));
    }

}
