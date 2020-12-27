package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.os.AsyncTask;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Async task to retrieve a user token.
 */
@AllArgsConstructor
public class AuthTokenTask extends AsyncTask<Void, Void, AuthToken> {

    /**
     * Manages users on device.
     */
    private final UserManager userManager;
    /**
     * Account for which the token should be retrieved.
     */
    private final Account account;

    /**
     * Where the token should be stored.
     */
    private final UploadListener uploadListener;

    /**
     * Retrieves a {@link AuthToken}.
     *
     * @param voids The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @SneakyThrows
    @Override
    protected AuthToken doInBackground(Void... voids) {
        return userManager.getAuthToken(account);
    }

    /**
     * Saves the {@link AuthToken} in the listener.
     *
     * @param token The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     */
    @Override
    protected void onPostExecute(AuthToken token) {
        super.onPostExecute(token);
        uploadListener.setAuthToken(token);
    }
}
