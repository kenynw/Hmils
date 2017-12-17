package com.cube.hmils.module.order;

import android.os.Bundle;
import android.widget.Button;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cube.hmils.model.constant.Extra.EXTRA_ORDER;
import static com.cube.hmils.model.constant.Extra.EXTRA_ROOM_NUM;

@RequiresPresenter(PickMaterialPresenter.class)
public class PickMaterialActivity extends ChainBaseActivity<PickMaterialPresenter> {

    @BindView(R.id.btn_type_next)
    Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_pick_material);
        ButterKnife.bind(this);
        setToolbarTitle("选择材料类型");

        Order order = getIntent().getParcelableExtra(EXTRA_ORDER);
        int num[] = getIntent().getIntArrayExtra(EXTRA_ROOM_NUM);
        mBtnNext.setOnClickListener(v -> {
            RoomParamsPresenter.start(this, order, num, 0);
            finish();
        });
    }

}
