package com.example.hyamaguchi.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.model.Discover;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

/**
 * Created by h.yamaguchi on 2018/03/28.
 */

public class DiscoverRecyclerViewAdapter extends RecyclerView.Adapter<DiscoverRecyclerViewAdapter.ViewHolder> {

    public interface Callback {
        void onClickList(Discover.DisplayInterface item, ImageView imageView);
    }

    private final Callback listener;
    private ArrayList<Discover.DisplayInterface> items;
    private Context context;

    public DiscoverRecyclerViewAdapter(Context context, Callback listener) {

        this.items = new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    public void setMovies(List<Discover.DisplayInterface> movies) {

        for (Discover.DisplayInterface movie : movies) {
            this.items.add(movie);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // ここでデータを反映
        holder.item = items.get(position);
        holder.titleTextView.setText(holder.item.title());
        holder.detailTextView.setText(holder.item.releaseDate());
        holder.voteTextView.setText(String.valueOf(holder.item.voteAverage()));
        Picasso.with(this.context).load(holder.item.imageUrl()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardView.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.cardView.setEnabled(true);
                    }
                }, 1000L);

                if (null != listener) {
                    listener.onClickList(holder.item, holder.imageView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final CardView cardView;
        public final ImageView imageView;
        public final TextView titleTextView;
        public final TextView detailTextView;
        public final TextView voteTextView;
        public Discover.DisplayInterface item;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.image_view);
            titleTextView = itemView.findViewById(R.id.item_title_text_view);
            detailTextView = itemView.findViewById(R.id.item_detail_text_view);
            voteTextView = itemView.findViewById(R.id.vote_average_text_view);
        }
    }
}
