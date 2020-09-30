package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;

/**
 * The main activity of this app.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView welcomeTextView = new TextView(this);

        AccountManager accountManager = AccountManager.get(this);
        UserManager userManager = new UserManager(accountManager);
        userManager.saveAccount(new LoginCredentials("marcel", "1234"));
        Account[] accounts = accountManager.getAccounts();

        String welcomeText = String.format("Welcome %s to Family Foto!", accounts[0].name);
        welcomeTextView.setText(welcomeText);
        setContentView(welcomeTextView);
    }
}
