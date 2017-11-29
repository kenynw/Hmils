package com.cube.hmils.module.order;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderList;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;

public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ClientModel.getInstance().getOrderList("", "").map(OrderList::getCustOrderList)
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {

    }

}
