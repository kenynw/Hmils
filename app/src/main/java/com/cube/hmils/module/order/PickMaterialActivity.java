package com.cube.hmils.module.order;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cube.hmils.model.constant.ExtraConstant.EXTRA_ORDER;
import static com.cube.hmils.model.constant.ExtraConstant.EXTRA_ROOM_NUM;

@RequiresPresenter(PickMaterialPresenter.class)
public class PickMaterialActivity extends ChainBaseActivity<PickMaterialPresenter> {

    @BindView(R.id.btn_type_next)
    Button mBtnNext;
    @BindView(R.id.rbtn_pick_material_1)
    RadioButton mRbtn1;
    @BindView(R.id.rbtn_pick_material_0)
    RadioButton mRbtn0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_pick_material);
        ButterKnife.bind(this);
        setToolbarTitle("选择材料类型");

        Order order = getIntent().getParcelableExtra(EXTRA_ORDER);
        int num[] = getIntent().getIntArrayExtra(EXTRA_ROOM_NUM);
        mBtnNext.setOnClickListener(v -> {
//            RoomParamsPresenter.start(this, order.getProjectId(), num, mRbtn0.isChecked() ? 0 : 1, 0);
            finish();
        });
    }

}
