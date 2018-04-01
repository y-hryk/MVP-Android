package com.example.hyamaguchi.mvp.screen.discoverDetail;

import com.example.hyamaguchi.mvp.model.Backdrop;
import com.example.hyamaguchi.mvp.model.Cast;
import com.example.hyamaguchi.mvp.model.Credit;
import com.example.hyamaguchi.mvp.model.Image;
import com.example.hyamaguchi.mvp.network.ApiClient;
import com.example.hyamaguchi.mvp.network.BackdropApi;
import com.example.hyamaguchi.mvp.network.CreditApi;
import com.example.hyamaguchi.mvp.screen.discover.DiscoverFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by h.yamguchi on 2018/04/02.
 */

interface DiscoverDetailView {
    void refreshBackDrop(List<Image> items);
    void refreshCredit(List<Cast> items);
}

interface DiscoverDetailPresenterInterface {

    void onCreate();
}


public class DiscoverDetailPresenter implements DiscoverDetailPresenterInterface {

    private DiscoverDetailView view;
    private DiscoverFragment.ContentsType type;
    private int id;

    public DiscoverDetailPresenter(DiscoverDetailView view, int id, DiscoverFragment.ContentsType type) {
        this.view = view;
        this.id = id;
        this.type = type;
    }

    @Override
    public void onCreate() {
        requestBackDrop();
        requestCredit();
    }

    private void requestBackDrop() {

        if (type == DiscoverFragment.ContentsType.Movie) {

            ApiClient.retrofit().create(BackdropApi.class).fetchImagesMovie(id)
                    .enqueue(new Callback<Backdrop>() {
                        @Override
                        public void onResponse(Call<Backdrop> call, Response<Backdrop> response) {
                            view.refreshBackDrop(response.body().backdrops);
                        }

                        @Override
                        public void onFailure(Call<Backdrop> call, Throwable t) {

                        }
                    });
        }

        if (type == DiscoverFragment.ContentsType.TV) {
            ApiClient.retrofit().create(BackdropApi.class).fetchImagesTV(id)
                    .enqueue(new Callback<Backdrop>() {
                        @Override
                        public void onResponse(Call<Backdrop> call, Response<Backdrop> response) {
                            view.refreshBackDrop(response.body().backdrops);
                        }

                        @Override
                        public void onFailure(Call<Backdrop> call, Throwable t) {

                        }
                    });

        }

    }

    private void requestCredit() {

        if (type == DiscoverFragment.ContentsType.Movie) {

            ApiClient.retrofit().create(CreditApi.class).fetchCreditsMovie(id)
                    .enqueue(new Callback<Credit>() {
                        @Override
                        public void onResponse(Call<Credit> call, Response<Credit> response) {
                            view.refreshCredit(response.body().cast);
                        }

                        @Override
                        public void onFailure(Call<Credit> call, Throwable t) {

                        }
                    });

        }

        if (type == DiscoverFragment.ContentsType.TV) {

            ApiClient.retrofit().create(CreditApi.class).fetchCreditsTV(id)
                    .enqueue(new Callback<Credit>() {
                        @Override
                        public void onResponse(Call<Credit> call, Response<Credit> response) {
                            view.refreshCredit(response.body().cast);
                        }

                        @Override
                        public void onFailure(Call<Credit> call, Throwable t) {

                        }
                    });
        }

    }
}
