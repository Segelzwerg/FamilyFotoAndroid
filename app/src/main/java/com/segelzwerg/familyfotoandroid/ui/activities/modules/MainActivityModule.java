package com.segelzwerg.familyfotoandroid.ui.activities.modules;

import com.segelzwerg.familyfotoandroid.familyfotoservice.FamilyFotoServerService;
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
     * @param server the host to this client
     * @return {@link FamilyFotoUploader}
     */
    @Provides
    public static Uploader provideUploader(FamilyFotoServerService server) {
        return new FamilyFotoUploader(server);
    }
}
