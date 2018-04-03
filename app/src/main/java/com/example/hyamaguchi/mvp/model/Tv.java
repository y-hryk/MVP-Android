package com.example.hyamaguchi.mvp.model;

import com.example.hyamaguchi.mvp.network.ApiClient;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by h.yamaguchi on 2018/03/29.
 */

public class Tv implements Serializable, Discover.DisplayInterface {

    public int id;
    public String name;

    public String overview;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("first_air_date")
    public String firstAirDate;

    //region Discover.DisplayInterface
    @Override
    public int id() {
        return id;
    }
    @Override
    public String title() {
        return name;
    }

    @Override
    public String releaseDate() {
        return firstAirDate;
    }

    @Override
    public String imageUrl() {
        return ApiClient.IMAGE_URL_W780 + backdropPath;
    }

    @Override
    public String overview() {
        return overview;
    }

    @Override
    public float voteAverage() {
        return voteAverage;
    }
    //endregion
}
