package com.segelzwerg.familyfotoandroid.ui.elements;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.segelzwerg.familyfotoandroid.R;

import java.io.File;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
    private final List<File> files;

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView view = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_viewholder, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.setImage(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }
}
