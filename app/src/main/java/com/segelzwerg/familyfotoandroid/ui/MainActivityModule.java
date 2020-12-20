package com.segelzwerg.familyfotoandroid.ui;

import com.segelzwerg.familyfotoandroid.familyfotoservice.FamilyFotoUploader;
import com.segelzwerg.familyfotoandroid.familyfotoservice.Uploader;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * Provides modules for the main activity.
 */
@Module
@InstallIn(ApplicationComponent.class)
public class MainActivityModule {
    /**
     * Provides an uploader to family foto server.
     *
     * @return {@link FamilyFotoUploader}
     */
    @Provides
    public static Uploader provideUploader() {
        return new FamilyFotoUploader();
    }
}
