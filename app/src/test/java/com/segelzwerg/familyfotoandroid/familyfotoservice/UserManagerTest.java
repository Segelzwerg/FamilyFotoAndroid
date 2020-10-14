package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserManagerTest {

    public static final String ACCOUNT_TYPE = "com.segelzwerg.familyfotoandroid";
    private AccountManager accountManager;
    private LoginCredentials credentials;
    private Account account;

    @BeforeEach
    public void setUp() {
        accountManager = mock(AccountManager.class);
        credentials = new LoginCredentials("marcel", "123123");
        account = new Account(credentials.getUsername(), ACCOUNT_TYPE);
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
    public void getAuthToken() {
        UserManager userManager = new UserManager(accountManager);
        AuthToken expectedToken = new AuthToken("token");
        AuthToken token = userManager.getAuthToken(account);
        assertThat(token).isEqualTo(expectedToken);
    }
}