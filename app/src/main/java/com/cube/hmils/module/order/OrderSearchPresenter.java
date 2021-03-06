package com.cube.hmils.module.order;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderResponse;
import com.dsk.chain.expansion.list.BaseListActivityPresenter;

/**
 * Created by Carol on 2017/11/12.
 */
public class OrderSearchPresenter extends BaseListActivityPresenter<OrderSearchActivity, Order> {

    @Override
    public void onRefresh() {
        ClientModel.getInstance().getOrderList(0, getView().getSearchKeywords(), 0)
                .map(OrderResponse::getCustOrderList)
                .unsafeSubscribe(getRefreshSubscriber());
    }

}
