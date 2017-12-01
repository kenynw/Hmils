package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.module.main.ServiceViewHolder;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Carol on 2017/10/14.
 */
@RequiresPresenter(OrderListPresenter.class)
public class OrderListFragment extends BaseListFragment<OrderListPresenter, Order> {

    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_STATE = "state";

    public static OrderListFragment newInstance(int type, int state) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TYPE, type);
        bundle.putInt(EXTRA_STATE, state);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BaseViewHolder<Order> createViewHolder(ViewGroup parent, int viewType) {
        return getPresenter().mType == 0 ? new OrderViewHolder(parent) : new ServiceViewHolder(parent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getListView().setClipToPadding(false);
        getListView().setRecyclerPadding(0, LUtils.dp2px(10), 0, 0);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setContainerEmptyRes(R.layout.def_empty_layout);
    }

}
