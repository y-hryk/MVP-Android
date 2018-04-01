package com.example.hyamaguchi.mvp.screen;

import android.content.Context;
import android.net.Uri;
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
import com.example.hyamaguchi.mvp.model.Tv;
import com.example.hyamaguchi.mvp.network.ApiClient;
import com.example.hyamaguchi.mvp.network.DiscoverApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TvListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TvListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TvListFragment extends Fragment implements DiscoverRecyclerViewAdapter.Callback  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TvListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TvListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TvListFragment newInstance(String param1, String param2) {
        TvListFragment fragment = new TvListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final DiscoverRecyclerViewAdapter adapter = new DiscoverRecyclerViewAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);

        ApiClient.retrofit().create(DiscoverApi.class).fetchDiscoverTv(1)
                .enqueue(new Callback<Discover<Tv>>() {
                    @Override
                    public void onResponse(Call<Discover<Tv>> call, Response<Discover<Tv>> response) {
                        Discover discover = response.body();
                        adapter.setItems(discover.items);
                    }

                    @Override
                    public void onFailure(Call<Discover<Tv>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClickList(Discover.DisplayInterface item, ImageView imageView) {

    }
}
