package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.Service;
import com.cube.hmils.model.bean.OrderResponse;
import com.cube.hmils.model.constant.ExtraConstant;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

/**
 * Created by Carol on 2017/11/8.
 */

public class ServiceDetailPresenter extends BaseDataActivityPresenter<ServiceDetailActivity, Service> {

    private int mOrderId;

    @Override
    protected void onCreate(ServiceDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mOrderId = view.getIntent().getIntExtra(ExtraConstant.EXTRA_ORDER_ID, 0);
    }

    @Override
    protected void onCreateView(ServiceDetailActivity view) {
        super.onCreateView(view);
        load();
    }

    private void load() {
        ClientModel.getInstance().getServiceDetail(mOrderId)
                .map(OrderResponse::getOrderInfo)
                .unsafeSubscribe(getDataSubscriber());
    }

}
