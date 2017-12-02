package com.cube.hmils.module.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cube.hmils.model.bean.RoomOrder;

import java.util.List;

/**
 * Copyright (c) 2017/12/2. LiaoPeiKun Inc. All rights reserved.
 */

public class ParamPageAdapter extends FragmentStatePagerAdapter {

    private List<RoomOrder> mOrderList;

    private List<RoomOrderFragment> mFragments;

    public ParamPageAdapter(FragmentManager fm, List<RoomOrder> roomOrders, List<RoomOrderFragment> fragments) {
        super(fm);
        mOrderList = roomOrders;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mOrderList.size();
    }

}
