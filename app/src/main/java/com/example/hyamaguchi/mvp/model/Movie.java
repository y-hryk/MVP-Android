package com.example.hyamaguchi.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by h.yamaguchi on 2018/03/28.
 */

public class Movie implements Serializable, Discover.DisplayInterface {

    public int id;
    public String title;

    public String overview;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("release_date")
    public String releaseDate;

    // Discover.DisplayInterface
    @Override
    public String title() {
        return title;
    }

    @Override
    public String releaseDate() {
        return releaseDate;
    }

    @Override
    public String imageUrl() {
        return "http://image.tmdb.org/t/p/w780" + backdropPath;
    }

    @Override
    public String overview() {
        return overview;
    }

    @Override
    public float voteAverage() {
        return voteAverage;
    }
}
