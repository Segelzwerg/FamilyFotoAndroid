package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;

import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;

import java.util.Objects;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Handles call backs after login requests.
 * @param <T> token that is returned from server
 */
class LoginCallBack<T extends AuthToken> implements Callback<AuthToken> {
    /**
     * Context from where the Callback is called.
     */
    private final transient Context context;
    /**
     * Handles all sorts of user request with Google API.
     */
    private final transient UserManager userManager;
    /**
     * The login credentials typed in the login form.
     */
    private final transient LoginCredentials loginCredentials;

    LoginCallBack(Context context, UserManager userManager, LoginCredentials loginCredentials) {
        this.context = context;
        this.userManager = userManager;
        this.loginCredentials = loginCredentials;
    }

    /**
     * Redirects to MainActivity.
     * {@inheritDoc}
     */
    @Override
    public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
        Intent intent = new Intent(context, MainActivity.class);
        Account account = userManager.getAccount(loginCredentials.getUsername());
        userManager.saveAuthToken(account, Objects.requireNonNull(response.body()));
        context.startActivity(intent, null);
    }

    /**
     * {@inheritDoc}
     **/
    @SneakyThrows
    @Override
    public void onFailure(Call<AuthToken> call, Throwable throwable) {
        throw throwable;
    }

}
