package com.cube.hmils.module.order;

import com.cube.hmils.model.bean.Order;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Order> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Order order = new Order();
            order.setClientName("张明");
            order.setTime("2017-02-03");
            list.add(order);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {

    }

}
