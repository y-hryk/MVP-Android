package com.example.hyamaguchi.mvp.network;

import com.example.hyamaguchi.mvp.model.Backdrop;
import com.example.hyamaguchi.mvp.model.Credit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by h.yamguchi on 2018/04/02.
 */

public interface CreditApi {

    @GET("movie/{movie_id}/credits?api_key=0a06fbb707cb2165dffcd8d27fd04365")
    Call<Credit> fetchCreditsMovie(@Path("movie_id") int movieId);

    @GET("tv/{tv_id}/credits?api_key=0a06fbb707cb2165dffcd8d27fd04365")
    Call<Credit> fetchCreditsTV(@Path("tv_id") int tvId);
}
