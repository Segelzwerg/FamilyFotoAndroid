package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;

import lombok.AllArgsConstructor;

/**
 * Handles user credentials.
 */
@AllArgsConstructor
public class UserManager {
    /**
     * Androids account manager.
     */
    private final AccountManager accountManager;

    /**
     * Saves the user credentials at the device.
     * @param credentials contains user's name and password.
     * @return the save Account
     */
    public Account saveAccount(LoginCredentials credentials) {
        Account account = new Account(credentials.getUsername(),
                "com.segelzwerg.familyfotoandroid");
        this.accountManager.addAccountExplicitly(account, credentials.getPassword(), null);
        return account;
    }


}
