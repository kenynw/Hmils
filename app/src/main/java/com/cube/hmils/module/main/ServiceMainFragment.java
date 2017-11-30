package com.cube.hmils.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cube.hmils.R;
import com.cube.hmils.module.order.OrderListFragment;
import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Carol on 2017/10/11.
 */
@RequiresPresenter(ServiceMainPresenter.class)
public class ServiceMainFragment extends ChainFragment<ServiceMainPresenter> {


    @BindView(R.id.tl_main_order)
    TabLayout mTlOrder;

    @BindView(R.id.vp_main_order)
    ViewPager mVpOrder;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_service, container, false);
        unbinder = ButterKnife.bind(this, view);

        TitlePagerAdapter adapter = new TitlePagerAdapter(getActivity(),R.array.text_order_service_tabs,
                getFragments(), getChildFragmentManager());
        mVpOrder.setAdapter(adapter);
        mTlOrder.setupWithViewPager(mVpOrder);

        return view;
    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        for (int i=0; i<4; i++) {
            OrderListFragment fragment = OrderListFragment.newInstance(1, 0);
            list.add(fragment);
            mTlOrder.addTab(mTlOrder.newTab());
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            if (menuVisible) {
                getView().setVisibility(View.VISIBLE);
            } else {
                getView().setVisibility(View.INVISIBLE);
            }
        }
    }


}
