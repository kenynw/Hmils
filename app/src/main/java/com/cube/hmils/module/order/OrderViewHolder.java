package com.cube.hmils.module.order;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Order;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/10/14.
 */

public class OrderViewHolder extends BaseViewHolder<Order> {

    @BindArray(R.array.order_status)
    String[] mOrderStatus;

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
    @BindView(R.id.tv_order_appo_time)
    TextView mTvAppoTime;
    @BindView(R.id.ll_order_appo_time)
    LinearLayout mLlAppoTime;

    public OrderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_order);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Order data) {
        mTvTime.setText(String.format(getContext().getString(R.string.text_order_time), data.getOrderTime()));
        if (data.getHandingStatus() == 5)
            mTvState.setTextColor(getContext().getResources().getColor(R.color.textTertiary));
        mTvState.setText(mOrderStatus[data.getHandingStatus() - 1]);
        mTvUsername.setText(data.getCustName());
        mTvContact.setText(data.getCustTel());
        mTvAddress.setText(data.getCustAddr());
        if (!TextUtils.isEmpty(data.getAppoTime())) {
            mLlAppoTime.setVisibility(View.VISIBLE);
            mTvAppoTime.setText("上门时间：" + data.getAppoTime());
        } else {
            mLlAppoTime.setVisibility(View.GONE);
        }
        mBtnDetail.setOnClickListener(v ->
                        OrderDetailPresenter.start(getContext(), data.getProjectId(), 1)
        );
        itemView.setOnClickListener(v ->
                ParamDetailPresenter.start(getContext(), data.getProjectId(), 1)
        );
    }

}
