package com.segelzwerg.familyfotoandroid.familyfotoservice;

import android.accounts.Account;
import android.view.View;

import lombok.Setter;

/**
 * Uploads images in background to the server.
 */
public class UploadListener implements View.OnClickListener {
    /**
     * Queue where images are stored for uploading.
     */
    private final transient UploaderQueue uploaderQueue;
    /**
     * Token required to authenticate to the server.
     */
    @Setter
    private transient AuthToken authToken = null;

    /**
     * Constructor.
     *
     * @param uploaderQueue where images are stored for uploading
     * @param userManager   manages users on device
     * @param account       of the current user
     */
    public UploadListener(UploaderQueue uploaderQueue,
                          UserManager userManager, Account account) {
        this.uploaderQueue = uploaderQueue;
        AuthTokenTask authTokenTask = new AuthTokenTask(userManager, account, this);
        authTokenTask.execute();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Header header = new Header();
        header.addToken(1, authToken.getToken());
        uploaderQueue.upload(header);
    }
}
