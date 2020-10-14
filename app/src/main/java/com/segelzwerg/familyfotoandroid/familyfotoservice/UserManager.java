package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

/**
 * Handles user credentials.
 */
@AllArgsConstructor
public class UserManager {
    /**
     * The account type used in this application.
     */
    private static final String ACCOUNT_TYPE = "com.segelzwerg.familyfotoandroid";
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
                ACCOUNT_TYPE);
        this.accountManager.addAccountExplicitly(account, credentials.getPassword(), null);
        return account;
    }

    /**
     * Retrieves the {@link AuthToken} for an user.
     * @param account the {@link Account} for whom the {@link AuthToken} should be returned
     * @param activity which activity needs the {@link AuthToken}
     * @return {@link AuthToken} for the user
     * @throws Exception is thrown if the user can not authenticate,
     *      or if the operation is canceled,
     *      or the input was invalid
     */
    public AuthToken getAuthToken(Account account, Activity activity) throws Exception {
        AccountManagerFuture<Bundle> accountManagerAuthToken = accountManager.getAuthToken(account,
                ACCOUNT_TYPE,
                null,
                activity,
                null,
                null);
        return extractResults(accountManagerAuthToken);
    }

    private AuthToken extractResults(AccountManagerFuture<Bundle> managerFuture) throws Exception {
        Bundle result = managerFuture.getResult();
        return tokenFromResults(result);
    }

    @NotNull
    private AuthToken tokenFromResults(Bundle result) {
        String tokenString = result.getString(AccountManager.KEY_AUTHTOKEN);
        return new AuthToken(tokenString);
    }
}
