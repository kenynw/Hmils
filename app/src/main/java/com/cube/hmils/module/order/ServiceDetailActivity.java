package com.cube.hmils.module.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

@RequiresPresenter(ServiceDetailPresenter.class)
public class ServiceDetailActivity extends BaseDataActivity<ServiceDetailPresenter, Order> {

    @BindView(R.id.iv_order_complete)
    ImageView mIvComplete;

    @BindViews({R.id.v_service_detail_marker_one, R.id.v_service_detail_marker_two, R.id.v_service_detail_marker_third})
    View[] mVMarkers;

    @BindViews({R.id.v_service_detail_line_one, R.id.v_service_detail_line_two})
    View[] mVLineOnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_service_detail);
        ButterKnife.bind(this);
    }

}
