package com.example.hyamaguchi.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.model.Cast;
import com.example.hyamaguchi.mvp.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h.yamguchi on 2018/04/02.
 */

public class CreditRecyclerViewAdapter extends RecyclerView.Adapter<CreditRecyclerViewAdapter.ViewHolder> {


    private ArrayList<Cast> items;
    private Context context;

    public CreditRecyclerViewAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setItems(List<Cast> casts) {

        for (Cast cast : casts) {
            this.items.add(cast);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credit_cell, parent, false);
        return new CreditRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Cast cast = items.get(position);
        Picasso.with(this.context).load("http://image.tmdb.org/t/p/w780" + cast.profilePath).into(holder.imageView);
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
