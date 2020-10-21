package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static com.segelzwerg.familyfotoandroid.utils.ReflectionUtil.setField;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginCallBackTest {

    public static final String ACCOUNT_TYPE = "com.segelzwerg.familyfotoandroid";
    private LoginCallBack<AuthToken> loginCallBack;
    private Context context;

    @BeforeEach
    public void setUp() throws AuthenticatorException, OperationCanceledException, IOException,
            NoSuchFieldException, IllegalAccessException {
        context = mock(Context.class);
        LoginCredentials loginCredentials = new LoginCredentials("marcel", "1234");
        AccountManager accountManager = mock(AccountManager.class);
        AccountManagerFuture<Bundle> managerFuture = mock(AccountManagerFuture.class);
        Bundle bundle = mock(Bundle.class);
        when(bundle.getString(AccountManager.KEY_AUTHTOKEN)).thenReturn("token");
        when(managerFuture.getResult()).thenReturn(bundle);
        Account account = mock(Account.class);
        setField(Account.class ,account, "name", "marcel");
        setField(Account.class, account, "type", ACCOUNT_TYPE);
        Account[] accounts = {account};
        when(accountManager.getAccounts()).thenReturn(accounts);
        when(accountManager.getAuthToken(eq(account),
                eq(ACCOUNT_TYPE),
                eq(null),
                eq(true),
                eq(null),
                eq(null))).thenReturn(managerFuture);
        UserManager userManager = new UserManager(accountManager);
        loginCallBack = new LoginCallBack<>(context, userManager, loginCredentials);
    }

    @Test
    public void onResponse() {
        Call<AuthToken> call = (Call<AuthToken>) mock(Call.class);
        AuthToken token = new AuthToken("token");
        Response<AuthToken> response = Response.success(token);
        loginCallBack.onResponse(call, response);
        verify(context, times(1))
                .startActivity(any(Intent.class), eq(null));
    }

    @Test
    public void onFailure() {
    }
}