package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.segelzwerg.familyfotoandroid.R;
import com.segelzwerg.familyfotoandroid.ui.LoginActivity;
import com.segelzwerg.familyfotoandroid.ui.MainActivity;
import com.segelzwerg.familyfotoandroid.ui.SettingsActivity;

import static androidx.core.content.ContextCompat.startActivity;

/**
 * Extends the {@link BottomNavigationView} to set destinations automatically.
 */
public class BottomNavigation extends BottomNavigationView {
    /**
     * Constructor.
     *
     * @param context where the field is initialized
     */
    public BottomNavigation(@NonNull Context context) {
        super(context);
    }

    /**
     * Constructor.
     *
     * @param context where the field is initialized
     * @param attrs   of the field
     */
    public BottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     *
     * @param context      where the field is initialized
     * @param attrs        of the field
     * @param defStyleAttr style of the field
     */
    public BottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Is called when attached to a window. It will automatically set the destinations to the
     * buttons.
     */
    @SuppressWarnings("PMD.DataFlowAnomalyAnalysis")
    @Override
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnNavigationItemSelectedListener(item -> {
            Context context = getContext();

            switch (item.getItemId()) {
                case R.id.nav_item_main:
                    startActivity(context, new Intent(context, MainActivity.class), null);
                    break;
                case R.id.nav_item_login:
                    startActivity(context, new Intent(context, LoginActivity.class), null);
                    break;
                case R.id.nav_item_settings:
                    startActivity(context, new Intent(context, SettingsActivity.class), null);
                    break;
                default:
                    return false;
            }
            return true;
        });
    }
}
