package com.cube.hmils.module.order;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Device;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ChangeDevicePresenter.class)
public class ChangeDeviceActivity extends ChainBaseActivity<ChangeDevicePresenter> implements TextWatcher {

    @BindView(R.id.sp_device_type)
    Spinner mSpType;
    @BindView(R.id.et_device_num)
    EditText mEtNum;
    @BindView(R.id.btn_room_next)
    Button mBtnNext;

    private Device mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_change_device);
        ButterKnife.bind(this);
        setToolbarTitle("更改设备");

        Order order = getIntent().getParcelableExtra("order");
        ArrayAdapter<Device> adapter = new ArrayAdapter<Device>(this, android.R.layout.simple_spinner_item, order.getHeatList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpType.setAdapter(adapter);
        mSpType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDevice = order.getHeatList().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mEtNum.addTextChangedListener(this);
        mBtnNext.setOnClickListener(v -> checkInput());
    }

    private void checkInput() {
        if (mEtNum.getText().length() <= 0) {
            LUtils.toast("请输入房间数");
            return;
        }
        getPresenter().changeHeat(mEtNum.getText().toString().trim());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mBtnNext.setEnabled(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public int[] getHideSoftViewIds() {
        return new int[] {R.id.et_device_num};
    }

    public Device getDevice() {
        return mDevice;
    }
}
