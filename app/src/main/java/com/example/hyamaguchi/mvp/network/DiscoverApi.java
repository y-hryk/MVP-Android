package com.example.hyamaguchi.mvp.network;

import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by h.yamaguchi on 2018/03/28.
 */

public interface DiscoverApi {

    String END_POINT = "https://api.themoviedb.org/3/";
    @GET("discover/movie?api_key=0a06fbb707cb2165dffcd8d27fd04365")
    Call<Discover<Movie>> fetchDiscover();
}
