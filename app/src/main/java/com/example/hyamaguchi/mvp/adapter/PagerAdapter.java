package com.example.hyamaguchi.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hyamaguchi.mvp.screen.discover.DiscoverFragment;

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
                return DiscoverFragment.create(DiscoverFragment.ContentsType.Movie);
            case 1:
                return DiscoverFragment.create(DiscoverFragment.ContentsType.TV);
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
                return "TV SHOW";
            default:
                return "";
        }
    }
}
