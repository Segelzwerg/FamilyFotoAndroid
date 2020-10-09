package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.accounts.AccountManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserManagerTest {

    private AccountManager accountManager;

    @BeforeEach
    public void setUp() {
        accountManager = mock(AccountManager.class);
    }

    @Test
    public void saveAccount() {
        LoginCredentials credentials = new LoginCredentials("marcel", "123123");
        UserManager userManager = new UserManager(accountManager);
        userManager.saveAccount(credentials);
        verify(accountManager, times(1))
                .addAccountExplicitly(any(Account.class),
                eq(credentials.getPassword()),
                eq(null));
    }
}