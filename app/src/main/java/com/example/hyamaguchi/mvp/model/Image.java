package com.example.hyamaguchi.mvp.model;

import com.example.hyamaguchi.mvp.network.ApiClient;
import com.google.gson.annotations.SerializedName;

/**
 * Created by h.yamguchi on 2018/04/02.
 */

public class Image {

    @SerializedName("file_path")
    public String filePath;

    public String fileURL() {
        return ApiClient.IMAGE_URL_W780 + filePath;
    }
}
