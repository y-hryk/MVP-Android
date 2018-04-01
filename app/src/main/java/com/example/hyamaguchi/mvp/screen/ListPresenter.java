package com.example.hyamaguchi.mvp.screen;

import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.model.Movie;
import com.example.hyamaguchi.mvp.network.ApiClient;
import com.example.hyamaguchi.mvp.network.DiscoverApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by h.yamaguchi on 2018/03/30.
 */

interface ListView {

    void startLoading();
    void stopLoading();
    void refreshView(List<Discover.DisplayInterface> items);
    void moreLoadCompletion(List<Discover.DisplayInterface> items);
}

interface ListPresenterInterface {

    void onViewCreated();
}

public class ListPresenter implements ListPresenterInterface {

    enum ContentsType {
        Movie,
        TV,
    };

    private ContentsType type;
    private ListView view;

    public ListPresenter(ListView lisner, ContentsType type) {
        this.view = lisner;
        this.type = type;
    }

    @Override
    public void onViewCreated() {

        requestDiscover();
    }

    private void requestDiscover() {

        ApiClient.retrofit().create(DiscoverApi.class).fetchDiscoverMovie(2)
                .enqueue(new Callback<Discover<Movie>>() {
                    @Override
                    public void onResponse(Call<Discover<Movie>> call, Response<Discover<Movie>> response) {
                        Discover discover = response.body();
                        view.refreshView(discover.items);
                    }

                    @Override
                    public void onFailure(Call<Discover<Movie>> call, Throwable t) {

                    }
                });
    }
}
