package com.example.hyamaguchi.mvp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by h.yamaguchi on 2018/03/29.
 */

public class ApiClient {

    public final static String END_POINT = "https://api.themoviedb.org/3/";
    public final static String API_KEY = "0a06fbb707cb2165dffcd8d27fd04365";
    public final static String IMAGE_URL_W780 = "http://image.tmdb.org/t/p/w780";

    public static Retrofit retrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //ログ出力設定
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
