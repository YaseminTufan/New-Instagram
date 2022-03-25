package com.yasemintufan.instagramfirst.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasemintufan.instagramfirst.R;
import com.yasemintufan.instagramfirst.model.ImageData;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewImageAdapter extends RecyclerView.Adapter<RecyclerviewImageAdapter.PhotosViewHolder> {
        List<ImageData> imageDataList = new ArrayList<>();
        public  RecyclerviewImageAdapter(Context context, List<ImageData> imageData){
            this.imageDataList = imageData;
        }
        @NonNull
        @Override
        public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_search,parent,false);
            return new PhotosViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
            holder.cardViewImage.setImageResource(imageDataList.get(position).getImage());

        }

        @Override
        public int getItemCount() {
            return imageDataList.size();
        }

        public class PhotosViewHolder extends RecyclerView.ViewHolder{
            ImageView cardViewImage;

            public PhotosViewHolder(@NonNull View itemView) {
                super(itemView);
                cardViewImage = itemView.findViewById(R.id.card_view_image);
            }
        }

    }


