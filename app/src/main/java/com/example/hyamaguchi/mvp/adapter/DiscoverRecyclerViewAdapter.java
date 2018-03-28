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
import com.example.hyamaguchi.mvp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h.yamaguchi on 2018/03/28.
 */

public class DiscoverRecyclerViewAdapter extends RecyclerView.Adapter<DiscoverRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Movie> items;
    private Context context;

    public DiscoverRecyclerViewAdapter(Context context) {

        this.items = new ArrayList<>();
        this.context = context;
    }

    public void setMovies(List<Movie> movies) {

        for (Movie movie : movies) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        // ここでデータを反映
        holder.movie = items.get(position);
        holder.titleTextView.setText(holder.movie.title);
        holder.detailTextView.setText(holder.movie.releaseDate);
        holder.voteTextView.setText(String.valueOf(holder.movie.voteAverage));
        Picasso.with(this.context).load("http://image.tmdb.org/t/p/w780" + holder.movie.backdropPath).into(holder.imageView);
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
        public Movie movie;

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
