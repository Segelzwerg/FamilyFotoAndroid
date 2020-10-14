package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserManagerTest {

    public static final String ACCOUNT_TYPE = "com.segelzwerg.familyfotoandroid";
    public static final String TOKEN_VALUE = "token";
    private AccountManager accountManager;
    private LoginCredentials credentials;
    private Account account;

    @BeforeEach
    public void setUp() throws AuthenticatorException, OperationCanceledException, IOException {
        accountManager = mock(AccountManager.class);
        credentials = new LoginCredentials("marcel", "123123");
        account = new Account(credentials.getUsername(), ACCOUNT_TYPE);
        AccountManagerFuture<Bundle> accountManagerFuture = mock(AccountManagerFuture.class);
        Bundle bundle = mock(Bundle.class);
        when(bundle.getString(AccountManager.KEY_AUTHTOKEN)).thenReturn(TOKEN_VALUE);
        when(accountManagerFuture.getResult()).thenReturn(bundle);
        when(accountManager.getAuthToken(eq(account),
                eq(ACCOUNT_TYPE),
                eq(null),
                any(Activity.class),
                eq(null),
                eq(null))).thenReturn(accountManagerFuture);
    }

    @Test
    public void saveAccount() {
        UserManager userManager = new UserManager(accountManager);
        Account savedUser = userManager.saveAccount(credentials);
        verify(accountManager, times(1))
                .addAccountExplicitly(any(Account.class),
                eq(credentials.getPassword()),
                eq(null));
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(account);
    }
    @Test
    public void getAuthToken() throws AuthenticatorException, OperationCanceledException, IOException {
        UserManager userManager = new UserManager(accountManager);
        AuthToken expectedToken = new AuthToken(TOKEN_VALUE);
        AuthToken token = userManager.getAuthToken(account, mock(Activity.class));
        assertThat(token).isEqualTo(expectedToken);
    }
}