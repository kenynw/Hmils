package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.module.dialog.BaseAlertDialog;
import com.cube.hmils.module.dialog.DialogCallback;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(RoomParamsPresenter.class)
public class RoomParamsActivity extends ChainBaseActivity<RoomParamsPresenter> implements DialogCallback {

    public static final String TAG_ADD_AREA = "add_area";

    @BindArray(R.array.letters)
    String[] LETTERS;

    @BindView(R.id.rb_room_params_steady)
    RadioButton mRbSteady;
    @BindView(R.id.rb_room_params_unsteady)
    RadioButton mRbUnsteady;
    @BindView(R.id.tv_room_params_bedroom)
    TextView mTvBedroom;
    @BindView(R.id.et_room_param_bedroom)
    EditText mEtBedroom;
    @BindView(R.id.tv_room_params_device)
    TextView mTvDevice;
    @BindView(R.id.et_room_param_device_name)
    EditText mEtDeviceName;
    @BindView(R.id.et_room_param_device_model)
    EditText mEtDeviceModel;
    @BindView(R.id.tv_room_params_width)
    TextView mTvWidth;
    @BindView(R.id.et_room_param_width)
    EditText mEtWidth;
    @BindView(R.id.tv_room_params_height)
    TextView mTvHeight;
    @BindView(R.id.et_room_param_height)
    EditText mEtHeight;
    @BindView(R.id.tv_room_params_add)
    TextView mTvAdd;
    @BindView(R.id.tv_room_params_minus)
    TextView mTvMinus;

    @BindView(R.id.ll_room_params_add)
    LinearLayout mLlAdd;
    @BindView(R.id.ibt_room_params_add)
    ImageButton mIbtAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity_params);
        ButterKnife.bind(this);

        mTvAdd.setOnClickListener(v -> showAddDialog());
        mIbtAdd.setOnClickListener(v -> {
            View view = View.inflate(this, R.layout.item_add_room, null);
            mLlAdd.addView(view);

            ImageView iv = view.findViewById(R.id.iv_add_room_delete);
            iv.setOnClickListener(view1 -> mLlAdd.removeView(view));

            TextView tvW = view.findViewById(R.id.tv_add_room_width);
            String letter = LETTERS[mLlAdd.indexOfChild(view)];
            tvW.setText(letter + getString(R.string.text_room_width));
            TextView tvH = view.findViewById(R.id.tv_add_room_height);
            tvH.setText(letter + getString(R.string.text_room_height));
        });
    }

    private void showAddDialog() {
        BaseAlertDialog.newInstance(R.layout.dialog_add_area, this)
                .show(getSupportFragmentManager(), TAG_ADD_AREA);
    }

    @Override
    public void onPositiveClick(@NonNull View view) {
        if (getSupportFragmentManager().findFragmentByTag(TAG_ADD_AREA) != null) {
            EditText et = view.findViewById(R.id.et_add_area_input);

        }
    }

    @Override
    public void onNegativeClick(@NonNull View view) {

    }

}
