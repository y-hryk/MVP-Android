package com.example.hyamaguchi.mvp.network;

import com.example.hyamaguchi.mvp.model.Backdrop;
import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by h.yamguchi on 2018/04/02.
 */

public interface BackdropApi {

    // 337167
    @GET("movie/{movie_id}/images?api_key=0a06fbb707cb2165dffcd8d27fd04365")
    Call<Backdrop> fetchImagesMovie(@Path("movie_id") int movieId);

    @GET("tv/{tv_id}/images?api_key=0a06fbb707cb2165dffcd8d27fd04365")
    Call<Backdrop> fetchImagesTV(@Path("tv_id") int tvId);
}
