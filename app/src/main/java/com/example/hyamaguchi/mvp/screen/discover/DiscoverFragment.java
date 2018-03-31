package com.example.hyamaguchi.mvp.screen.discover;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.adapter.DiscoverRecyclerViewAdapter;
import com.example.hyamaguchi.mvp.model.Discover;

import java.util.List;

public class DiscoverFragment extends Fragment implements DiscoverRecyclerViewAdapter.Callback, DiscoverView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_CONTENTS_TYPE = "type";

    public enum ContentsType {
        Movie,
        TV,
    };

    private DiscoverPresenterInterface presenter;
    private DiscoverRecyclerViewAdapter adapter;

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
        this.presenter = new DiscoverPresenter(this, type);
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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new DiscoverRecyclerViewAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);

        this.presenter.onViewCreated();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // DiscoverRecyclerViewAdapter.Callback
    @Override
    public void onClickList(Discover.DisplayInterface item, ImageView imageView) {

    }

    // DiscoverView
    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void refreshView(List<Discover.DisplayInterface> items) {
        adapter.setMovies(items);
    }

    @Override
    public void moreLoadCompletion(List<Discover.DisplayInterface> items) {

    }
}
