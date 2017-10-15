package com.cube.hmils.module.main;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by LPK on 2016/11/21.
 */

public class TitlePagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    private String[] mTitles;

    private List<Fragment> mFragments;

    public TitlePagerAdapter(Context context, @ArrayRes int resId, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTitles = mContext.getResources().getStringArray(resId);
    }

    public TitlePagerAdapter( Context context, String[] titles, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTitles = titles;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
