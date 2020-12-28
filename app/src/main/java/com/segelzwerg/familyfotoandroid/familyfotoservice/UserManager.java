package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
     * Key in user data for the ID.
     */
    public static final String USER_ID_KEY = "userId";
    /**
     * Androids account manager.
     */
    private final AccountManager accountManager;

    /**
     * Saves the user credentials at the device.
     *
     * @param credentials contains user's name and password.
     * @return the save Account
     */
    public Account saveAccount(LoginCredentials credentials) {
        Account account = new Account(credentials.getUsername(),
                ACCOUNT_TYPE);
        Bundle userData = new Bundle();
        userData.putInt(USER_ID_KEY, credentials.getUserId());
        this.accountManager.addAccountExplicitly(account, credentials.getPassword(), userData);
        return account;
    }

    /**
     * Retrieves the user ID of an user.
     *
     * @param account for which the ID is requested.
     * @return the id as an int.
     */
    public int getUserId(Account account) {
        return Integer.parseInt(accountManager.getUserData(account, USER_ID_KEY));
    }

    /**
     * Retrieves the {@link AuthToken} for an user.
     *
     * @param account the {@link Account} for whom the {@link AuthToken} should be returned
     * @return {@link AuthToken} for the user
     * @throws ManagerExtractionException is thrown if the user can not authenticate,
     *                                    or if the operation is canceled,
     *                                    or the input was invalid
     */
    public AuthToken getAuthToken(Account account) throws ManagerExtractionException {
        AccountManagerFuture<Bundle> accountManagerAuthToken = accountManager.getAuthToken(account,
                ACCOUNT_TYPE,
                null,
                true,
                null,
                null);
        return extractResults(accountManagerAuthToken);
    }

    private AuthToken extractResults(AccountManagerFuture<Bundle> managerFuture) throws ManagerExtractionException {
        try {
            Bundle result = managerFuture.getResult();
            return tokenFromResults(result);

        } catch (AuthenticatorException | IOException | OperationCanceledException e) {
            String message = String.format("Could not get response from %s", managerFuture);
            throw new ManagerExtractionException(message, e);
        }
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
