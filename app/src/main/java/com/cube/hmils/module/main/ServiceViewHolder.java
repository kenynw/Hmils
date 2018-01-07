package com.cube.hmils.module.main;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.Service;
import com.cube.hmils.model.constant.Extra;
import com.cube.hmils.module.order.ServiceDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/11/8.
 */

public class ServiceViewHolder extends BaseViewHolder<Service> {

    @BindArray(R.array.order_status)
    String[] mOrderStatus;

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

    public ServiceViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_order_service);
        ButterKnife.bind(this, itemView);
        mIvLineBottom.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mIvLineTop.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public void setData(Service data) {
        mTvTime.setText(String.format(getContext().getString(R.string.text_appoint_time), data.getTime()));
        mTvState.setText(data.getCodeName());
        mTvUsername.setText(data.getCustName());
        mTvContact.setText(data.getContTel());
        mTvAddress.setText(data.getContAddr());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ServiceDetailActivity.class);
            intent.putExtra(Extra.EXTRA_ORDER_ID, data.getOrderId());
            getContext().startActivity(intent);
        });
    }

}
