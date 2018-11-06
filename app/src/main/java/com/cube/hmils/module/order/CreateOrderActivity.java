package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;

public class CreateOrderActivity extends ChainBaseActivity<CreateOrderPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_create);
    }

}
