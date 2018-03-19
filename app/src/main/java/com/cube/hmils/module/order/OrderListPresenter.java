package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;

import static com.cube.hmils.module.order.OrderListFragment.EXTRA_STATE;
import static com.cube.hmils.module.order.OrderListFragment.EXTRA_USER_ID;

public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    private int mCustId;
    private int mState;

    @Override
    protected void onCreate(OrderListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);

        mCustId = view.getArguments().getInt(EXTRA_USER_ID, 0);
        mState = view.getArguments().getInt(EXTRA_STATE, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ClientModel.getInstance().getOrderList(mCustId, "", mState)
                .map(orderResponse -> orderResponse != null ? orderResponse.getCustOrderList() : null)
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
