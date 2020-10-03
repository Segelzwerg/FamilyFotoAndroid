package com.segelzwerg.familyfotoandroid.accounts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * {@inheritDoc}.
 */
public class FamilyFotoService extends Service {
    /**
     * lock mechanism.
     */
    private static final Object LOCK = new Object();
    /**
     * Handles account authentication.
     */
    private transient FamilyFotoAuthenticator authenticator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();

        synchronized (LOCK) {
            if (authenticator == null) {
                authenticator = new FamilyFotoAuthenticator(this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
