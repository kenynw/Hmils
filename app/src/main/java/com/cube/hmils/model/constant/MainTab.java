package com.cube.hmils.model.constant;

import com.cube.hmils.R;
import com.cube.hmils.module.main.ClientListFragment;
import com.cube.hmils.module.main.OrderMainFragment;
import com.cube.hmils.module.main.ServiceListFragment;

/**
 * Copyright (c) 2017/3/17. LiaoPeiKun Inc. All rights reserved.
 */

public enum MainTab {

    HOME(0, R.drawable.tab_main_home_selector, R.string.text_client_order, OrderMainFragment.class),
    MODULE(1, R.drawable.tab_main_client_selector, R.string.text_my_client, ClientListFragment.class),
    TEST(2, R.drawable.tab_main_services_selector, R.string.text_my_services, ServiceListFragment.class),
    ME(3, R.drawable.tab_main_me_selector, R.string.text_me, ServiceListFragment.class);

    public final int mTabIndex;

    public final int mIconRes;

    public final int mStrRes;

    public final Class mFragment;

    MainTab(int tabIndex, int iconRes, int strRes, Class fragment) {
        mTabIndex = tabIndex;
        mIconRes = iconRes;
        mStrRes = strRes;
        mFragment = fragment;
    }

    public static MainTab tabFromIndex(int index) {
        for (MainTab mainTab : MainTab.values()) {
            if (mainTab.mTabIndex == index) return mainTab;
        }
        return null;
    }

}
