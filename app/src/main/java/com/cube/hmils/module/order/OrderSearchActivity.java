package com.cube.hmils.module.order;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cube.hmils.R;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;

@RequiresPresenter(OrderSearchPresenter.class)
public class OrderSearchActivity extends BaseListActivity<OrderSearchPresenter> {

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(name, context, attrs);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setContainerLayoutRes(R.layout.order_activity_search);
    }

}
