package com.cube.hmils.module.order;

import android.view.ViewGroup;

import com.cube.hmils.R;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(OrderDetailPresenter.class)
public class OrderDetailActivity extends BaseListActivity<OrderDetailPresenter> {

    @Override
    protected int getLayout() {
        return R.layout.order_activity_detail;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

}
