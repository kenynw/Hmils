package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

@RequiresPresenter(PickMaterialPresenter.class)
public class PickMaterialActivity extends ChainBaseActivity<PickMaterialPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_pick_material);
    }

}
