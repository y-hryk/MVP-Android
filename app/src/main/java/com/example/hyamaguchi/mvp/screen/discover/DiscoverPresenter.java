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
    void clearContents();
}

interface DiscoverPresenterInterface {

    void onViewCreated();
    void moreLoad(int page);
    void refresh();
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
        requestDiscover(1);
    }

    @Override
    public void moreLoad(int page) {
        requestDiscover(page);
    }

    public void refresh() {
        view.clearContents();
        requestDiscover(1);
    }

    private void requestDiscover(int page) {

        if (this.type == DiscoverFragment.ContentsType.Movie) {

            ApiClient.retrofit().create(DiscoverApi.class).fetchDiscoverMovie(page)
                    .enqueue(new Callback<Discover<Movie>>() {
                        @Override
                        public void onResponse(Call<Discover<Movie>> call, Response<Discover<Movie>> response) {
                            view.stopLoading();
                            Discover discover = response.body();
                            view.refreshView(discover.items);
                        }

                        @Override
                        public void onFailure(Call<Discover<Movie>> call, Throwable t) {
                            view.stopLoading();
                        }
                    });
        }

        if (this.type == DiscoverFragment.ContentsType.TV) {

            ApiClient.retrofit().create(DiscoverApi.class).fetchDiscoverTv(page)
                    .enqueue(new Callback<Discover<Tv>>() {
                        @Override
                        public void onResponse(Call<Discover<Tv>> call, Response<Discover<Tv>> response) {
                            view.stopLoading();
                            Discover discover = response.body();
                            view.refreshView(discover.items);
                        }

                        @Override
                        public void onFailure(Call<Discover<Tv>> call, Throwable t) {
                            view.stopLoading();
                        }
                    });
        }
    }
}


