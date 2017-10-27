package com.cube.hmils.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.cube.hmils.R;
import com.cube.hmils.module.account.LoginActivity;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends ChainBaseActivity<MainPresenter> implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.fl_main_container)
    FrameLayout mFlContainer;

    @BindView(R.id.tl_main_indicator)
    TabLayout mTlIndicator;

    private MainTabPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        ButterKnife.bind(this);

        mPagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager(), this);
        mTlIndicator.addOnTabSelectedListener(this);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTlIndicator.newTab();
            mTlIndicator.addTab(tab);
            tab.setCustomView(R.layout.item_tab_main)
                    .setIcon(mPagerAdapter.getIconRes(i))
                    .setText(mPagerAdapter.getPageTitle(i));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() != 3) {
            int position = tab.getPosition();
            Fragment fragment = (Fragment) mPagerAdapter.instantiateItem(mFlContainer, position);
            mPagerAdapter.setPrimaryItem(mFlContainer, position, fragment);
            mPagerAdapter.finishUpdate(mFlContainer);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
