package com.ahir.testapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahir.testapp.R;
import com.ahir.testapp.fragments.HomeFragment;
import com.ahir.testapp.model.ListItem;
import com.bumptech.glide.Glide;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public  class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.NewViewHolder> {
    Context context;
    ArrayList<ListItem> items ;


    public ItemAdapter(Context context, ArrayList<ListItem> items){
        this.context = context;
        this.items = items;

    }

    public void setItems(ArrayList<ListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.NewViewHolder holder, int position) {
        ListItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.price.setText( String.valueOf(item.getPrice()));
        holder.category.setText(item.getCategory());
//        holder.imageView.setImageBitmap(getBitmapFromURL(item.getImageUrl()));
        Glide.with(context)
                .load(item.getImageUrl())
                .into(holder.imageView);

//        Image from the URL not assigned yet!




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, category,price;
        ImageView imageView;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}