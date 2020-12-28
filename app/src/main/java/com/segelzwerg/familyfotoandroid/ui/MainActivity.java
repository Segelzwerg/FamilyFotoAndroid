package com.segelzwerg.familyfotoandroid.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.ManagerExtractionException;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UploadListener;
import com.segelzwerg.familyfotoandroid.familyfotoservice.Uploader;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UploaderQueue;
import com.segelzwerg.familyfotoandroid.familyfotoservice.UserManager;
import com.segelzwerg.familyfotoandroid.imageservice.ImageScraper;
import com.segelzwerg.familyfotoandroid.imageservice.utils.ImageLoaderUtil;
import com.segelzwerg.familyfotoandroid.ui.elements.GalleryLayout;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * The main activity of this app.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    /**
     * Path to the SD CARD.
     */
    private static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getPath();
    /**
     * Path to the camera directory.
     */
    private static final String DCIM_PATH = String.format("%s/DCIM/Camera", SD_CARD_PATH);
    /**
     * Manages account provides by Google API.
     */
    private transient UserManager userManager;
    /**
     * Current user account.
     */
    private transient Account account;

    /**
     * Service that uploads the files.
     */
    // Dagger can't inject in private fields.
    @SuppressWarnings({"checkstyle:VisibilityModifier", "PMD.DefaultPackage"})
    @Inject
    transient Uploader uploader;
    /**
     * Queue where all file to be uploaded are collected.
     */
    private transient UploaderQueue uploaderQueue;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_main);
        GalleryLayout gallery = findViewById(R.id.gallery);
        Button uploadButton = (Button) findViewById(R.id.uploadBtn);

        AccountManager accountManager = AccountManager.get(this);
        userManager = new UserManager(accountManager);
        userManager.saveAccount(new LoginCredentials(1, "admin", "admin"));

        uploaderQueue = new UploaderQueue(uploader);
        account = userManager.getAccount("admin");
        UploadListener uploadListener = new UploadListener(uploaderQueue, userManager, account);
        setListener(uploadButton, uploadListener);


        ImageScraper imageScraper;

        try {
            imageScraper = new ImageScraper(DCIM_PATH, uploaderQueue);
            imageScraper.startWatching();
        } catch (IOException e) {
            Log.e("Error", e.getMessage(), e);
        }

        try {
            List<File> files = ImageLoaderUtil.loadImages(DCIM_PATH);
            gallery.addImages(files);
        } catch (IOException e) {
            Log.e("Error", e.getMessage(), e);
        }


    }

    private void setListener(Button uploadButton, UploadListener uploadListener) {
        uploadButton.setOnClickListener(uploadListener);
    }

    /**
     * Gets the auth token for the current user.
     *
     * @return {@link AuthToken}
     * @throws ManagerExtractionException the auth token could not be extracted from the response
     */
    public AuthToken getAuthToken() throws ManagerExtractionException {
        return userManager.getAuthToken(account);
    }

    /**
     * Queue of images to be uploaded.
     *
     * @return {@link UploaderQueue}
     */
    public UploaderQueue getUploaderQueue() {
        return uploaderQueue;
    }
}
