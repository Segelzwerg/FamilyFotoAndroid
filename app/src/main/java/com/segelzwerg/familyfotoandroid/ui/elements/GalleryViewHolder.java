package com.segelzwerg.familyfotoandroid.ui.elements;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.segelzwerg.familyfotoandroid.imageservice.utils.PicassoAdapter;

import java.io.File;

public class GalleryViewHolder extends RecyclerView.ViewHolder {

    /**
     * Height of the images.
     */
    private static final int TARGET_HEIGHT = 250;
    /**
     * Width of the images.
     */
    private static final int TARGET_WIDTH = 250;
    @NonNull
    private final ImageView itemView;

    public GalleryViewHolder(@NonNull ImageView itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void setImage(File file) {
        PicassoAdapter.intoView(file, TARGET_WIDTH, TARGET_HEIGHT, itemView);
        FlexboxLayoutManager.LayoutParams layoutParams = (FlexboxLayoutManager.LayoutParams) itemView.getLayoutParams();
        layoutParams.setFlexGrow(0.2f);
        layoutParams.setMaxWidth(TARGET_WIDTH);
    }
}
