package com.example.hyamaguchi.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by h.yamguchi on 2018/04/02.
 */

public class Cast {

    @SerializedName("cast_id")
    public int castId;

    public String name;

    @SerializedName("profile_path")
    public String profilePath;
}
