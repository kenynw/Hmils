package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderList;
import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;

import static com.cube.hmils.module.order.OrderListFragment.EXTRA_STATE;

public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {


    private int mState;

    @Override
    protected void onCreate(OrderListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);

        mState = view.getArguments().getInt(EXTRA_STATE);
    }

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ClientModel.getInstance().getOrderList("", mState).map(OrderList::getCustOrderList)
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.ORDER_LIST_UPDATE) {
            onRefresh();
        }
    }

}
