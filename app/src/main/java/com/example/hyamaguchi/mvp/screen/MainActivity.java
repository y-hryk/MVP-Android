package com.example.hyamaguchi.mvp.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.adapter.DiscoverRecyclerViewAdapter;
import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.network.DiscoverApi;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DiscoverRecyclerViewAdapter adapter = new DiscoverRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //ログ出力設定
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DiscoverApi.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Call<Discover> call = retrofit.create(DiscoverApi.class).fetchDiscover();
        call.enqueue(new Callback<Discover>() {
            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {
                Discover discover = response.body();
                adapter.setMovies(discover.movies);
                Log.d("debug", "アイテム名" + discover.movies.get(0).title);
            }

            @Override
            public void onFailure(Call<Discover> call, Throwable t) {

            }
        });
//        call.enqueue(new Callback<List<Discover>>() {
//            @Override
//            public void onResponse(Call<List<Discover>> call, Response<List<Discover>> response) {
//                List<Discover> items = response.body();
//                adapter.setMovies(items);
//
//                Log.d("debug", "アイテム名" + items.get(0).title);
//            }
//
//            @Override
//            public void onFailure(Call<List<Discover>> call, Throwable t) {
//
//                Log.d("debug", "失敗" + t.getMessage());
//            }
//        });
    }


}

