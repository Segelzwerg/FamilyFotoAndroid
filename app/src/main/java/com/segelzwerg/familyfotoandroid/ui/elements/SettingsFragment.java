package com.segelzwerg.familyfotoandroid.ui.elements;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.segelzwerg.familyfotoandroid.R;

/**
 * Fragment containing the settings defined in the preference.xml.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    /**
     * Called during {@link #onCreate(Bundle)} to supply the preferences for this fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *                           this is the state.
     * @param rootKey            If non-null, this preference fragment should be rooted at the
     *                           PreferenceScreen with this key.
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }
}
