package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;

/**
 * Handles user credentials.
 */
@AllArgsConstructor
public class UserManager {
    /**
     * The account type used in this application.
     */
    public static final String ACCOUNT_TYPE = "com.segelzwerg.familyfotoandroid";
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
     * @return {@link AuthToken} for the user
     * @throws Exception is thrown if the user can not authenticate,
     *      or if the operation is canceled,
     *      or the input was invalid
     */
    public AuthToken getAuthToken(Account account) throws Exception {
        AccountManagerFuture<Bundle> accountManagerAuthToken = accountManager.getAuthToken(account,
                ACCOUNT_TYPE,
                null,
                true,
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

    /**
     * Saves the auth token for an user.
     * @param account to which the token belongs.
     * @param authToken the token itself.
     */
    public void saveAuthToken(Account account, AuthToken authToken) {
        accountManager.setAuthToken(account, ACCOUNT_TYPE, authToken.getToken());
    }

    /**
     * Gets the account for a given username.
     * @param username name of the request account.
     * @return {@link Account}
     */
    // This is a stream and no violation.
    @SuppressWarnings("PMD.LawOfDemeter")
    public Account getAccount(String username) {
        Account[] accounts = accountManager.getAccounts();
        Optional<Account> optionalAccount = Arrays.stream(accounts)
                .filter(account -> account.name.equals(username))
                .findFirst();
        return optionalAccount.orElse(null);
    }
}
