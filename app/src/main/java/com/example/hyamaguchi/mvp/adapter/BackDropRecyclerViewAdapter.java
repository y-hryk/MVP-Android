package com.example.hyamaguchi.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h.yamguchi on 2018/04/01.
 */

public class BackDropRecyclerViewAdapter extends RecyclerView.Adapter<BackDropRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Image> items;

    public BackDropRecyclerViewAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setItems(List<Image> items) {

        for (Image image : items) {
            this.items.add(image);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.backdrop_cell, parent, false);
        return new BackDropRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Image image = items.get(position);
        Picasso.with(this.context).load("http://image.tmdb.org/t/p/w780" + image.filePath).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
