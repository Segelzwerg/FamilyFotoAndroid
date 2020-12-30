package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthTokenResponse;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;
import com.segelzwerg.familyfotoandroid.ui.activities.MainActivity;

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
public class LoginCallBack<T extends AuthTokenResponse> implements Callback<AuthTokenResponse> {
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

    public LoginCallBack(Context context, UserManager userManager, LoginCredentials loginCredentials) {
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
    public void onResponse(@NotNull Call<AuthTokenResponse> call,
                           @NotNull Response<AuthTokenResponse> response) {
        Intent intent = new Intent(context, MainActivity.class);
        Account account = userManager.getAccount(loginCredentials.getUsername());
        AuthTokenResponse body = response.body();

        if (account == null) {
            int userId = body.getUserId();
            LoginCredentials updatedCredentials = new LoginCredentials(userId,
                    this.loginCredentials.getUsername(),
                    this.loginCredentials.getPassword());
            account = userManager.saveAccount(updatedCredentials);
        }
        if (response.code() == HTTP_UNAUTHORIZED) {
            Log.e("ERROR", "Could authorize.");
        } else {
            userManager.saveAuthToken(account, body.getToken());
            context.startActivity(intent, null);
        }
    }

    /**
     * {@inheritDoc}
     **/
    @SneakyThrows
    @Override
    public void onFailure(Call<AuthTokenResponse> call, Throwable throwable) {
        throw throwable;
    }

}
