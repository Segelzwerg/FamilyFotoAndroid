package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.ManagerExtractionException;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;
import com.segelzwerg.familyfotoandroid.imageservice.utils.ImageLoaderUtil;
import com.segelzwerg.familyfotoandroid.ui.elements.GalleryLayout;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The main activity of this app.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Manages account provides by Google API.
     */
    private transient UserManager userManager;
    /**
     * Current user account.
     */
    private transient Account account;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_main);
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        GalleryLayout gallery = findViewById(R.id.gallery);


        try {
            List<File> files = ImageLoaderUtil.loadImages("/storage/emulated/0/DCIM/Camera");
            gallery.addImages(files);
        } catch (IOException e) {
            Log.e("Error", e.getMessage(), e);
        }

        AccountManager accountManager = AccountManager.get(this);
        userManager = new UserManager(accountManager);
        account = userManager.saveAccount(new LoginCredentials("marcel", "1234"));

        String welcomeText = "Welcome to Family Foto!";
        welcomeTextView.setText(welcomeText);
    }

    /**
     * Gets the auth token for the current user.
     * @return {@link AuthToken}
     * @throws ManagerExtractionException the auth token could not be extracted from the response
     */
    public AuthToken getAuthToken() throws ManagerExtractionException {
        return userManager.getAuthToken(account);
    }
}
