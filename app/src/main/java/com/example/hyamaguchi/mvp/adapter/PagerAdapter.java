package com.example.hyamaguchi.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hyamaguchi.mvp.screen.MovieListFragment;
import com.example.hyamaguchi.mvp.screen.TvListFragment;

/**
 * Created by h.yamaguchi on 2018/03/30.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MovieListFragment();
            case 1:
                return new TvListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MOVIE";
            case 1:
                return "TV";
            default:
                return "";
        }
    }
}
