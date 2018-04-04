package com.example.hyamaguchi.mvp.screen.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.adapter.DiscoverRecyclerViewAdapter;
import com.example.hyamaguchi.mvp.listener.EndlessScrollListener;
import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.screen.discoverDetail.DiscoverDetailActivity;

import java.util.List;

public class DiscoverFragment extends Fragment implements DiscoverRecyclerViewAdapter.Callback, DiscoverView {

    private static final String ARG_CONTENTS_TYPE = "type";

    public enum ContentsType {
        Movie,
        TV,
    };

    private DiscoverPresenterInterface presenter;
    private RecyclerView recyclerView;
    private DiscoverRecyclerViewAdapter adapter;
    private SwipeRefreshLayout refresh;
    private EndlessScrollListener endlessScrollListener;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment create(ContentsType type) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTENTS_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentsType type = ContentsType.Movie;
        if (getArguments() != null) {
            type = (ContentsType)getArguments().get(ARG_CONTENTS_TYPE);
        }

        presenter = new DiscoverPresenter(this, type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
        setRecyclerView();
        setRefreshView();

        presenter.onViewCreated();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void bindViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        refresh = view.findViewById(R.id.refresh);
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DiscoverRecyclerViewAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);

        endlessScrollListener = new EndlessScrollListener((LinearLayoutManager)recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int current_page) {
                // Load
                Log.d("debug", "ページング: " + current_page);
                presenter.moreLoad(current_page);
            }
        };
        recyclerView.addOnScrollListener(endlessScrollListener);
    }

    private void setRefreshView() {

        refresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
    }

    // DiscoverRecyclerViewAdapter.Callback
    @Override
    public void onClickList(Discover.DisplayInterface item, ImageView imageView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                imageView,
                "trasition_image" );

        Intent intent = new Intent(getActivity(), DiscoverDetailActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("type", (ContentsType)getArguments().get(ARG_CONTENTS_TYPE));
        startActivity(intent, options.toBundle());
    }

    // DiscoverView
    @Override
    public void startLoading() {
        refresh.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        refresh.setRefreshing(false);
    }

    @Override
    public void refreshView(List<Discover.DisplayInterface> items) {
        adapter.setItems(items);
    }

    @Override
    public void clearContents() {
        endlessScrollListener.clear();
        adapter.clearItems();
    }
}
