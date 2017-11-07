package com.cube.hmils.module.main;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.module.order.ServiceDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/11/8.
 */

public class ServiceViewHolder extends BaseViewHolder<Order> {

    @BindView(R.id.tv_order_time)
    TextView mTvTime;
    @BindView(R.id.tv_order_state)
    TextView mTvState;
    @BindView(R.id.iv_order_line_top)
    View mIvLineTop;
    @BindView(R.id.dv_order_avatar)
    SimpleDraweeView mDvAvatar;
    @BindView(R.id.tv_order_username)
    TextView mTvUsername;
    @BindView(R.id.tv_order_contact)
    TextView mTvContact;
    @BindView(R.id.tv_order_address)
    TextView mTvAddress;
    @BindView(R.id.iv_order_line_bottom)
    View mIvLineBottom;
    @BindView(R.id.btn_order_detail)
    Button mBtnDetail;

    public ServiceViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_order_service);
        ButterKnife.bind(this, itemView);
        mIvLineBottom.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mIvLineTop.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public void setData(Order data) {
        mTvTime.setText(String.format(getContext().getString(R.string.text_order_time), data.getTime()));
        mTvState.setText("未完成");
        mTvUsername.setText("张明");
        mTvContact.setText("15375870891");
        mTvAddress.setText("地址：厦门思明区软件园望");
        itemView.setOnClickListener(v -> getContext().startActivity(new Intent(getContext(), ServiceDetailActivity.class)));
    }

}