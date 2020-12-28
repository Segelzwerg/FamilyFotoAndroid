package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;

import org.jetbrains.annotations.NotNull;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;


/**
 * Handles call backs after login requests.
 *
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
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    public void onResponse(@NotNull Call<AuthToken> call, @NotNull Response<AuthToken> response) {
        Intent intent = new Intent(context, MainActivity.class);
        Account account = userManager.getAccount(loginCredentials.getUsername());
        if (account == null) {
            account = userManager.saveAccount(loginCredentials);
        }
        if (response.code() == HTTP_UNAUTHORIZED) {
            Log.e("ERROR", "Could authorize.");
        } else {
            userManager.saveAuthToken(account, response.body());
            context.startActivity(intent, null);
        }
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
