package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;

import butterknife.ButterKnife;

/**
 * @author Liaopeikun
 *         <p>
 *         总订单详情
 */
@RequiresPresenter(OrderDetailPresenter.class)
public class OrderDetailActivity extends BaseDataActivity<OrderDetailPresenter, RoomOrder> {

    private int mProjectId;

    RoomOrderFragment mOrderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_detail);
        ButterKnife.bind(this);

        mProjectId = getIntent().getIntExtra("projectId", 0);

        mOrderFragment = RoomOrderFragmentPresenter.newInstance(mProjectId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_order_detail_container, mOrderFragment)
                .commit();
    }

    @Override
    public void setData(RoomOrder roomOrder) {

    }

}
