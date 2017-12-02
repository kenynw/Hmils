package com.cube.hmils.module.order;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Device;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carol on 2017/11/22.
 */

public class DeviceRecyclerAdapter extends RecyclerArrayAdapter<Device> {

    private boolean mIsEdit; // 编辑模式

    private List<Device> mModifyItem;

    public DeviceRecyclerAdapter(Context context, boolean isEdit) {
        super(context);
        mIsEdit = isEdit;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceViewHolder(parent);
    }

    public List<Device> getModifyItem() {
        return mModifyItem;
    }

    public class DeviceViewHolder extends BaseViewHolder<Device> implements TextWatcher {

        @BindView(R.id.tv_device_spec)
        TextView mTvSpec;
        @BindView(R.id.tv_device_count)
        TextView mTvCount;
        @BindView(R.id.tv_device_pr)
        TextView mTvPr;
        @BindView(R.id.tv_device_price)
        TextView mTvPrice;
        @BindView(R.id.btn_item_device_add)
        Button mBtnItemDeviceAdd;
        @BindView(R.id.btn_item_device_minus)
        Button mBtnItemDeviceMinus;

        public DeviceViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_list_device);
            ButterKnife.bind(this, itemView);

            mModifyItem = new ArrayList<>();
            mTvCount.addTextChangedListener(this);

            mBtnItemDeviceAdd.setVisibility(mIsEdit ? View.VISIBLE : View.GONE);
            mBtnItemDeviceMinus.setVisibility(mIsEdit ? View.VISIBLE : View.GONE);
        }

        @Override
        public void setData(Device data) {
            mTvSpec.setText(data.getSpec());
            mTvCount.setText(String.valueOf(data.getQty()));
            mTvPr.setText(data.getPowerRating());
            mTvPrice.setText(data.getPrice());

            mBtnItemDeviceAdd.setOnClickListener(v -> {
                int count = Integer.valueOf(mTvCount.getText().toString().trim()) + 1;
                mTvCount.setText(String.valueOf(count));
                data.setQty(count);
                if (!mModifyItem.contains(data)) mModifyItem.add(data);
            });
            mBtnItemDeviceMinus.setOnClickListener(v -> {
                int count = Integer.valueOf(mTvCount.getText().toString().trim()) - 1;
                mTvCount.setText(String.valueOf(count));
                data.setQty(count);
                if (!mModifyItem.contains(data)) mModifyItem.add(data);
            });
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int count = Integer.valueOf(mTvCount.getText().toString().trim());
            mBtnItemDeviceMinus.setEnabled(count > 1);
        }

    }

}
