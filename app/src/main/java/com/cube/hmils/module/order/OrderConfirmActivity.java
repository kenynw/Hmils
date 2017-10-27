package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

@RequiresPresenter(OrderConfirmPresenter.class)
public class OrderConfirmActivity extends ChainBaseActivity<OrderConfirmPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_confirm);
    }

}
