package com.cube.hmils.module.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(RoomNumPersenter.class)
public class RoomNumActivity extends ChainBaseActivity<RoomNumPersenter> {

    @BindView(R.id.et_room_num)
    EditText mEtNum;

    @BindView(R.id.btn_room_next)
    Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_num);
        ButterKnife.bind(this);

        mBtnNext.setOnClickListener(v -> startActivity(new Intent(this, RoomParamsActivity.class)));
    }

}
