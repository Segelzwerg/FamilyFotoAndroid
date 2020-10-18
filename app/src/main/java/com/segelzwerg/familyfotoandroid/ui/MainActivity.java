package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;

/**
 * The main activity of this app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Manages account provides by Google API.
     */
    private transient UserManager userManager;
    /**
     * Current user account.
     */
    private transient Account account;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView welcomeTextView = new TextView(this);

        AccountManager accountManager = AccountManager.get(this);
        userManager = new UserManager(accountManager);
        account = userManager.saveAccount(new LoginCredentials("marcel", "1234"));

        String welcomeText = "Welcome to Family Foto!";
        welcomeTextView.setText(welcomeText);
        setContentView(welcomeTextView);
    }

    /**
     * Gets the auth token for the current user.
     * @return {@link AuthToken}
     * @throws Exception collections of things can go wrong.
     */
    public AuthToken getAuthToken() throws Exception {
        return userManager.getAuthToken(account);
    }
}
