package com.cube.hmils.module.order;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/10/14.
 */

public class OrderViewHolder extends BaseViewHolder<Order> {

    @BindView(R.id.tv_order_time)
    TextView mTvTime;
    @BindView(R.id.ll_order_header)
    LinearLayout mLlHeader;
    @BindView(R.id.tv_order_username)
    TextView mTvUsername;
    @BindView(R.id.tv_order_contact)
    TextView mTvContact;
    @BindView(R.id.tv_order_address)
    TextView mTvAddress;
    @BindView(R.id.ll_order_user)
    LinearLayout mLlUser;
    @BindView(R.id.btn_order_detail)
    Button mBtnDetail;
    @BindView(R.id.tv_order_state)
    TextView mTvState;

    public OrderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_order);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Order data) {
        mTvTime.setText(String.format(getContext().getString(R.string.text_order_time), data.getOrderTime()));
        mTvState.setText(data.getHandingStatus());
        mTvUsername.setText(data.getCustName());
        mTvContact.setText(data.getCustTel());
        mTvAddress.setText(data.getCustAddr());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), RoomParamsActivity.class);
            intent.putExtra("order", data);
            getContext().startActivity(intent);
        });
    }

}
