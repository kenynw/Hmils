package com.cube.hmils.module.order;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeDeviceActivity extends ChainBaseActivity<ChangeDevicePresenter> {

    @BindView(R.id.tv_device_type)
    TextView mTvDeviceType;
    @BindView(R.id.et_device_num)
    EditText mEtDeviceNum;
    @BindView(R.id.btn_room_next)
    Button mBtnRoomNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_change_device);
        ButterKnife.bind(this);
        setToolbarTitle("更改设备");
    }

}
