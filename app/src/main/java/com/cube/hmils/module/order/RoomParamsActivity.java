package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Room;
import com.cube.hmils.module.dialog.BaseAlertDialog;
import com.cube.hmils.module.dialog.DialogCallback;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.btn_room_params_save)
    Button mBtnSave;

    @BindView(R.id.ll_room_params_add)
    LinearLayout mLlAdd;
    @BindView(R.id.ibt_room_params_add)
    ImageButton mIbtAdd;
    @BindView(R.id.toolbar_back_icon)
    ImageView mToolbarBackIcon;
    @BindView(R.id.toolbar_back_text)
    TextView mToolbarBackText;

    @BindView(R.id.cl_params_layout)
    ConstraintLayout mClLayout;
    @BindView(R.id.tv_room_params_extra)
    TextView mTvExtra;
    @BindView(R.id.et_room_param_extra)
    EditText mEtExtra;
    @BindView(R.id.ll_params_extra)
    LinearLayout mLlExtra;

    private List<Room> mRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity_params);
        ButterKnife.bind(this);

        mRooms = new ArrayList<>();

        mToolbarBackIcon.setVisibility(View.GONE);
        mToolbarBackText.setVisibility(View.VISIBLE);
        mToolbarBackText.setText("上一步");
        mTvAdd.setOnClickListener(v -> showAddDialog());
        mTvMinus.setOnClickListener(v -> {
            mLlExtra.setVisibility(View.GONE);
            mEtExtra.setText("");
            mClLayout.getLayoutParams().height = mClLayout.getHeight() - LUtils.dp2px(70);
        });
        mIbtAdd.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.item_add_room, null);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mLlAdd.addView(view);

            ImageView iv = view.findViewById(R.id.iv_add_room_delete);
            iv.setOnClickListener(view1 -> {
                mLlAdd.removeView(view);
                mClLayout.getLayoutParams().height = mClLayout.getHeight() - view.getMeasuredHeight();
            });

            TextView tvW = view.findViewById(R.id.tv_add_room_width);
            String letter = LETTERS[mLlAdd.indexOfChild(view)];
            tvW.setText(letter + getString(R.string.text_room_width));
            TextView tvH = view.findViewById(R.id.tv_add_room_height);
            tvH.setText(letter + getString(R.string.text_room_height));

            mClLayout.getLayoutParams().height = mClLayout.getHeight() + view.getMeasuredHeight();
        });
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void showAddDialog() {
        BaseAlertDialog.newInstance(R.layout.dialog_add_area, this)
                .show(getSupportFragmentManager(), TAG_ADD_AREA);
    }

    @Override
    public void onPositiveClick(@NonNull View view) {
        if (getSupportFragmentManager().findFragmentByTag(TAG_ADD_AREA) != null) {
            EditText et = view.getRootView().findViewById(R.id.et_add_area_input);
            mLlExtra.setVisibility(View.VISIBLE);
            mEtExtra.setText(et.getText());
            mClLayout.getLayoutParams().height = mClLayout.getHeight() + LUtils.dp2px(70);
        }
    }

    @Override
    public void onNegativeClick(@NonNull View view) {

    }

    private void checkInput() {
        String roomName = mEtBedroom.getText().toString().trim();
        String roomWidth = mEtWidth.getText().toString().trim();
        String roomHeight = mEtHeight.getText().toString().trim();
        mRooms.clear();
        addRoom(roomWidth, roomHeight);
        if (mLlAdd.getChildCount() > 0) {
            for (int i = 0; i < mLlAdd.getChildCount(); i++) {
                View view = mLlAdd.getChildAt(i);
                TextView tvW = view.findViewById(R.id.tv_add_room_width);
                TextView tvH = view.findViewById(R.id.tv_add_room_height);
                String width = tvW.getText().toString().trim();
                String height = tvH.getText().toString().trim();
                if (!TextUtils.isEmpty(width) && !TextUtils.isEmpty(height)) {
                    addRoom(width, height);
                }
            }
        }
        String roomSize = new Gson().toJson(mRooms);
        getPresenter().saveParams("", roomName, roomSize, 0);
    }

    private void addRoom(String width, String height) {
        Room room = new Room();
        room.setWidth(width);
        room.setHeight(height);
        mRooms.add(room);
    }

}
