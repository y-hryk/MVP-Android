package com.example.hyamaguchi.mvp.screen;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.adapter.DiscoverRecyclerViewAdapter;
import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.model.Movie;
import com.example.hyamaguchi.mvp.network.ApiClient;
import com.example.hyamaguchi.mvp.network.DiscoverApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DiscoverRecyclerViewAdapter.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DiscoverRecyclerViewAdapter adapter = new DiscoverRecyclerViewAdapter(this, this);
        recyclerView.setAdapter(adapter);

        Call<Discover<Movie>> call = ApiClient.retrofit().create(DiscoverApi.class).fetchDiscover();

        call.enqueue(new Callback<Discover<Movie>>() {
            @Override
            public void onResponse(Call<Discover<Movie>> call, Response<Discover<Movie>> response) {
                Discover discover = response.body();
                adapter.setMovies(discover.movies);
//                Log.d("debug", "アイテム名" + discover.movies.get(0).title);
            }

            @Override
            public void onFailure(Call<Discover<Movie>> call, Throwable t) {

            }
        });

//        call.enqueue(new Callback<Discover>() {
//            @Override
//            public void onResponse(Call<Discover> call, Response<Discover> response) {
//                Discover discover = response.body();
//                adapter.setMovies(discover.movies);
//                Log.d("debug", "アイテム名" + discover.movies.get(0).title);
//            }
//
//            @Override
//            public void onFailure(Call<Discover> call, Throwable t) {
//
//            }
//        });
    }

    // DiscoverRecyclerViewAdapter.Callback
    @Override
    public void onClickList(Movie movie, ImageView imageView) {
        Log.d("debug", "click" + movie);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                "trasition_image" );

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent, options.toBundle());
    }
}

