package com.example.hyamaguchi.mvp.screen.discoverDetail;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyamaguchi.mvp.R;
import com.example.hyamaguchi.mvp.adapter.BackDropRecyclerViewAdapter;
import com.example.hyamaguchi.mvp.adapter.CreditRecyclerViewAdapter;
import com.example.hyamaguchi.mvp.model.Cast;
import com.example.hyamaguchi.mvp.model.Discover;
import com.example.hyamaguchi.mvp.model.Image;
import com.example.hyamaguchi.mvp.screen.discover.DiscoverFragment;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DiscoverDetailActivity extends AppCompatActivity implements DiscoverDetailView {

    private DiscoverDetailPresenterInterface presenter;

    private RecyclerView backDropRecyclerView;
    private RecyclerView creditRecyclerView;
    private BackDropRecyclerViewAdapter backDropAdapter;
    private CreditRecyclerViewAdapter creditAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_detail);

        Intent intent = getIntent();
        final Discover.DisplayInterface item = (Discover.DisplayInterface) intent.getSerializableExtra("item");
        DiscoverFragment.ContentsType type = (DiscoverFragment.ContentsType)intent.getSerializableExtra("type");
        presenter = new DiscoverDetailPresenter(this, item.id(), type);

        bindViews();
        setToolBar(item);
        setDetailInfo(item);
        setBackDrop();
        setCredit();

        presenter.onCreate();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void bindViews() {
        backDropRecyclerView = findViewById(R.id.backdrop_recyclerView);
        creditRecyclerView = findViewById(R.id.credit_recyclerView);
    }

    private void setToolBar(Discover.DisplayInterface item) {

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final String title = item.title();
        final CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbarLayout.setTitle(title);
                    isShow = true;
                } else if(isShow) {
                    toolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    private void setDetailInfo(Discover.DisplayInterface item) {

        ImageView imageView = findViewById(R.id.image_view);
        Picasso.with(this).load(item.imageUrl()).into(imageView);

        TextView titleTextView = findViewById(R.id.title_text_view);
        titleTextView.setText(item.title());

        TextView detailTextView = findViewById(R.id.detail_text_view);
        detailTextView.setText(item.overview());
    }

    private void setBackDrop() {

        backDropRecyclerView.setHasFixedSize(true);
        backDropRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        backDropAdapter = new BackDropRecyclerViewAdapter(this);
        backDropRecyclerView.setAdapter(backDropAdapter);

        backDropRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                outRect.bottom = 10;
                outRect.left = 10;
                outRect.right = 10;
            }
        });
    }

    private void setCredit() {

        creditRecyclerView.setHasFixedSize(true);
        creditRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        creditAdapter = new CreditRecyclerViewAdapter(this);
        creditRecyclerView.setAdapter(creditAdapter);

        creditRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                outRect.bottom = 10;
                outRect.left = 10;
                outRect.right = 10;
            }
        });
    }

    // DiscoverDetailView
    @Override
    public void refreshBackDrop(List<Image> items) {
        backDropAdapter.setItems(items);
    }
    @Override
    public  void refreshCredit(List<Cast> items) {
        creditAdapter.setItems(items);
    }
}
