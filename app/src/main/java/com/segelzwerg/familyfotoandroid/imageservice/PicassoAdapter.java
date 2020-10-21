package com.segelzwerg.familyfotoandroid.imageservice;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Adapter for {@link Picasso}
 */
public class PicassoAdapter {

    /**
     * Loads an image to an {@link ImageView} and center crops it.
     * @param file the image file to be loaded
     * @param targetWidth width of the image to which it will be cropped
     * @param targetHeight height of the image to which it will be cropped
     * @param imageView the {@link ImageView} where the image will be added to
     */
    public static void intoView(File file, int targetWidth, int targetHeight, ImageView imageView) {
        Picasso.get()
                .load(file)
                .resize(targetWidth, targetHeight)
                .centerCrop()
                .into(imageView);
    }
}
