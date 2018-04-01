package com.example.hyamaguchi.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by h.yamaguchi on 2018/03/28.
 */


public class Discover<T> implements Serializable {

    public interface DisplayInterface extends Serializable {
        int id();
        String title();
        String releaseDate();
        String imageUrl();
        String overview();
        float voteAverage();
    }

    public int page;
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("results")
    public List<T> items;
}
