package com.cube.hmils.module.order;

import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Device;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/11/22.
 */

public class DeviceViewHolder extends BaseViewHolder<Device> {

    @BindView(R.id.tv_device_spec)
    TextView mTvSpec;
    @BindView(R.id.tv_device_count)
    TextView mTvCount;
    @BindView(R.id.tv_device_pr)
    TextView mTvPr;
    @BindView(R.id.tv_device_price)
    TextView mTvPrice;

    public DeviceViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_device);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Device data) {
        mTvSpec.setText(data.getSpec());
        mTvCount.setText(data.getQty() + "");
        mTvPr.setText(data.getPowerRating());
        mTvPrice.setText(data.getPrice());
    }
}
