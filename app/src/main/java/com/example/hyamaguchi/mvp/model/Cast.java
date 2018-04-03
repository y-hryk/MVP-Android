package com.example.hyamaguchi.mvp.model;

import com.example.hyamaguchi.mvp.network.ApiClient;
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


    public String fileURL() {
        return ApiClient.IMAGE_URL_W780 + profilePath;
    }
}
