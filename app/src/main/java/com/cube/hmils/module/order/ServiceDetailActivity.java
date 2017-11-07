package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;

@RequiresPresenter(ServiceDetailPresenter.class)
public class ServiceDetailActivity extends BaseDataActivity<ServiceDetailPresenter, Order> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_service_detail);
    }

}