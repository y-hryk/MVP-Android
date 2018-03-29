package com.example.hyamaguchi.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by h.yamaguchi on 2018/03/29.
 */

public class Tv {

    public int id;
    public String title;

    public String overview;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("release_date")
    public String releaseDate;
}
