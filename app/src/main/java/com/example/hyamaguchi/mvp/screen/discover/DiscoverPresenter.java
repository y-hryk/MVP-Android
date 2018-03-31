package com.example.hyamaguchi.mvp.screen.discover;

import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.model.Movie;
import com.example.hyamaguchi.mvp.model.Tv;
import com.example.hyamaguchi.mvp.network.ApiClient;
import com.example.hyamaguchi.mvp.network.DiscoverApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by h.yamguchi on 2018/03/31.
 */

interface DiscoverView {
    void startLoading();
    void stopLoading();
    void refreshView(List<Discover.DisplayInterface> items);
    void moreLoadCompletion(List<Discover.DisplayInterface> items);
}

interface DiscoverPresenterInterface {

    void onViewCreated();
}

public class DiscoverPresenter implements DiscoverPresenterInterface {

    private DiscoverView view;
    private DiscoverFragment.ContentsType type;

    public DiscoverPresenter(DiscoverView view, DiscoverFragment.ContentsType type) {
        this.view = view;
        this.type = type;
    }

    // DiscoverPresenterInterface
    @Override
    public void onViewCreated() {
        requestDiscover();
    }

    private void requestDiscover() {

        if (this.type == DiscoverFragment.ContentsType.Movie) {

            ApiClient.retrofit().create(DiscoverApi.class).fetchDiscoverMovie()
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

        if (this.type == DiscoverFragment.ContentsType.TV) {

            ApiClient.retrofit().create(DiscoverApi.class).fetchDiscoverTv()
                    .enqueue(new Callback<Discover<Tv>>() {
                        @Override
                        public void onResponse(Call<Discover<Tv>> call, Response<Discover<Tv>> response) {
                            Discover discover = response.body();
                            view.refreshView(discover.items);
                        }

                        @Override
                        public void onFailure(Call<Discover<Tv>> call, Throwable t) {

                        }
                    });
        }
    }
}


