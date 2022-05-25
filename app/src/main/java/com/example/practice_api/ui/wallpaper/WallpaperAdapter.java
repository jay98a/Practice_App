package com.example.practice_api.ui.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice_api.R;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperViewHolder> {

    private Context context;
    private List<WallpaperModel> wallpaperModelslist;

    public WallpaperAdapter(Context context, List<WallpaperModel> wallpaperModelslist) {
        this.context = context;
        this.wallpaperModelslist = wallpaperModelslist;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_item_view, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WallpaperViewHolder holder, final int position) {
        Glide.with(context).load(wallpaperModelslist.get(position).getMediumUrl()).into(holder.imageView);
        holder.imageView.setBackground(null);
        //Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,Image_details.class);
//                intent.putExtra("image_id",wallpaperModelslist.get(position).getId());
//                intent.putExtra("medium_url",wallpaperModelslist.get(position).getMediumUrl());
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wallpaperModelslist.size();
    }
}

class WallpaperViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView_wallpaper_item);
    }
}