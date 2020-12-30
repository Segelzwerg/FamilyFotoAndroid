package com.segelzwerg.familyfotoandroid.ui.elements;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.segelzwerg.familyfotoandroid.imageservice.utils.PicassoAdapter;

import java.io.File;
import java.util.List;

/**
 * Extension of {@link FlexboxLayoutManager}. It displays thumbnails of the images added.
 */

public class GalleryLayout extends FlexboxLayoutManager {
    /**
     * Outer scope context where this layout is initialized.
     */
    private Context context;
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
        this.context = context;
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
        ImageView imageView = new ImageView(this.context);
        PicassoAdapter.intoView(file, TARGET_WIDTH, TARGET_HEIGHT, imageView);
        this.addView(imageView);
    }
}
