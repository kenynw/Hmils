package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderList;
import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;

import static com.cube.hmils.module.order.OrderListFragment.EXTRA_STATE;
import static com.cube.hmils.module.order.OrderListFragment.EXTRA_TYPE;
import static com.cube.hmils.module.order.OrderListFragment.EXTRA_USER_ID;

public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    private int mUserId;
    public int mType;
    private int mState;

    @Override
    protected void onCreate(OrderListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);

        mUserId = view.getArguments().getInt(EXTRA_USER_ID);
        mState = view.getArguments().getInt(EXTRA_STATE);
        mType = view.getArguments().getInt(EXTRA_TYPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        if (mType == 0) {
            ClientModel.getInstance().getOrderList(mUserId, "", mState).map(OrderList::getCustOrderList)
                    .unsafeSubscribe(getRefreshSubscriber());
        } else {
            ClientModel.getInstance().getServiceList(mState).map(OrderList::getCustOrderList)
                    .unsafeSubscribe(getRefreshSubscriber());
        }
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
