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
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     *
     * <p>
     * This will normally run on a background thread. But to better
     * support testing frameworks, it is recommended that this also tolerates
     * direct execution on the foreground thread, as part of the {@link #execute} call.
     *
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
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
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.
     * To better support testing frameworks, it is recommended that this be
     * written to tolerate direct execution as part of the execute() call.
     * The default version does nothing.</p>
     *
     * <p>This method won't be invoked if the task was cancelled.</p>
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
