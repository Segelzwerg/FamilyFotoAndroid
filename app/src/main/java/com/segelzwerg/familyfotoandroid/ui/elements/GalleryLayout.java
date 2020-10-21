package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.segelzwerg.familyfotoandroid.imageservice.PicassoAdapter;

import java.io.File;
import java.util.List;

/**
 * Extension of {@link LinearLayout}. It displays thumbnails of the images added.
 */

public class GalleryLayout extends LinearLayout {
    /**
     * Height of the images.
     */
    private static final int TARGET_HEIGHT = 250;
    /**
     * Width of the images.
     */
    private static final int TARGET_WIDTH = 250;
    /**
     * Constructor.
     * @param context where the layout is placed.
     */
    public GalleryLayout(Context context) {
        super(context);
    }

    /**
     * Constructor.
     * @param context where the layout is placed.
     * @param attrs attribute set
     */
    public GalleryLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor.
     * @param context where the layout is placed.
     * @param attrs attribute set
     * @param defStyleAttr def style attribute
     */
    public GalleryLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Constructor.
     * @param context where the layout is placed.
     * @param attrs attribute set
     * @param defStyleAttr def style attribute
     * @param defStyleRes def style resolution
     */
    public GalleryLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Adds images to the gallery.
     * @param files list of files to be added
     */
    //This is a stream not a LoD violation.
    @SuppressWarnings("PMD.LawOfDemeter")
    public void addImages(List<File> files) {
        files.forEach(this::addImageToView);
    }

    //This is a stream not a LoD violation.
    @SuppressWarnings("PMD.LawOfDemeter")
    private void addImageToView(File file) {
        if (!file.exists()) {
            String message = String.format("File does not exists: %s", file.getAbsolutePath());
            Log.e("FILE", message);
            return;
        }
        ImageView imageView = new ImageView(super.getContext());
        PicassoAdapter.intoView(file, TARGET_WIDTH, TARGET_HEIGHT, imageView);
        this.addView(imageView);
    }
}
