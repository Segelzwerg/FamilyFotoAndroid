package com.segelzwerg.familyfotoandroid.accounts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FamilyFotoService extends Service {
    private static final Object LOCK = new Object();
    private FamilyFotoAuthenticator authenticator;

    @Override
    public void onCreate() {
        super.onCreate();

        synchronized (LOCK) {
            if (authenticator == null) {
                authenticator = new FamilyFotoAuthenticator(this);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
