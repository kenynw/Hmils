package com.cube.hmils.module.order;

import android.view.ViewGroup;

import com.cube.hmils.model.bean.Order;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Carol on 2017/10/14.
 */
@RequiresPresenter(OrderListPresenter.class)
public class OrderListFragment extends BaseListFragment<OrderListPresenter, Order> {

    @Override
    public BaseViewHolder<Order> createViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(parent);
    }

}
