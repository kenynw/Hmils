package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;

public class ChangeDeviceActivity extends ChainBaseActivity<ChangeDevicePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_change_device);
        setToolbarTitle("更改设备");
    }

}
