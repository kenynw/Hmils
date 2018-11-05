package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Params;
import com.cube.hmils.model.bean.Room;
import com.cube.hmils.module.dialog.DialogCallback;
import com.cube.hmils.module.dialog.ExtraAreaDialog;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(RoomParamsPresenter.class)
public class RoomParamsActivity extends ChainBaseActivity<RoomParamsPresenter> implements DialogCallback,
        RadioGroup.OnCheckedChangeListener {

    public static final String TAG_ADD_AREA = "add_area";
    public static final String TAG_MINU_AREA = "minu_area";

    @BindArray(R.array.letters)
    String[] LETTERS;

    @BindView(R.id.rg_room_params_type)
    RadioGroup mRgType;
    @BindView(R.id.rb_room_params_steady)
    RadioButton mRbSteady;
    @BindView(R.id.rb_room_params_unsteady)
    RadioButton mRbUnsteady;
    @BindView(R.id.tv_room_params_bedroom)
    TextView mTvBedroom;
    @BindView(R.id.et_room_param_bedroom)
    EditText mEtBedroom;
    @BindView(R.id.tv_room_params_width)
    TextView mTvWidth;
    @BindView(R.id.et_room_param_long)
    EditText mEtLong;
    @BindView(R.id.tv_room_params_height)
    TextView mTvHeight;
    @BindView(R.id.et_room_param_width)
    EditText mEtWidth;
    @BindView(R.id.tv_room_params_add)
    TextView mTvAdd;
    @BindView(R.id.tv_room_params_minus)
    TextView mTvMinus;

    @BindView(R.id.rg_params_mater_type)
    RadioGroup mRgMaterType;
    @BindView(R.id.rbtn_params_mater_0)
    RadioButton mRgMaterType0;
    @BindView(R.id.rbtn_params_mater_1)
    RadioButton mRgMaterType1;

    @BindView(R.id.rg_params_floor_type)
    RadioGroup mRgFloorType;
    @BindView(R.id.rbtn_params_floor_0)
    RadioButton mRgFloorType0;
    @BindView(R.id.rbtn_params_floor_1)
    RadioButton mRgFloorType1;

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
    @BindView(R.id.ll_params_extra)
    LinearLayout mLlExtra;

    private List<Room> mRooms;

    private List<Room> mAddAreas; // 增加面积
    private List<Room> mMinuAreas; // 减少面积

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity_params);
        ButterKnife.bind(this);

        mRooms = new ArrayList<>();

        mToolbarBackIcon.setVisibility(View.GONE);
        mToolbarBackText.setVisibility(View.VISIBLE);
        mToolbarBackText.setText("上一步");

        mRgType.setOnCheckedChangeListener(this);
        mTvAdd.setOnClickListener(v -> showAddDialog(0));
        mTvMinus.setOnClickListener(v -> showAddDialog(1));
        mIbtAdd.setOnClickListener(v -> addRoomLayout("", ""));
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void showAddDialog(int type) {
        ExtraAreaDialog.newInstance(type)
                .show(getSupportFragmentManager(), type == 0 ? TAG_ADD_AREA : TAG_MINU_AREA);
    }

    @Override
    public void onPositiveClick(@NonNull View view) {
        EditText etWidth = view.getRootView().findViewById(R.id.et_add_area_width);
        EditText etHeight = view.getRootView().findViewById(R.id.et_add_area_height);
        int width = Integer.valueOf(etWidth.getText().toString().trim());
        int height = Integer.valueOf(etHeight.getText().toString().trim());

        if (getSupportFragmentManager().findFragmentByTag(TAG_ADD_AREA) != null) {
            addAndMinusArea(0, width + "*" + height);
        } else if (getSupportFragmentManager().findFragmentByTag(TAG_MINU_AREA) != null) {
            addAndMinusArea(1, width + "*" + height);
        }
        LUtils.closeKeyboard(etWidth);
    }

    @Override
    public void onNegativeClick(@NonNull View view) {

    }

    public void setData(Params params) {
        if (!TextUtils.isEmpty(params.getName())) mEtBedroom.setText(params.getName());

        if (params.getRooms() != null && params.getRooms().size() > 0) {
            mEtLong.setText(params.getRooms().get(0).getLong());
            mEtWidth.setText(params.getRooms().get(0).getWidth());
            if (params.getIsSteady() == 1) {
                mRgType.check(R.id.rb_room_params_steady);
            } else {
                mRgType.check(R.id.rb_room_params_unsteady);
                for (int i = 1; i < params.getRooms().size(); i++) {
                    Room room = params.getRooms().get(i);
                    addRoomLayout(room.getLong(), room.getWidth());
                }
            }
        }

        if (params.getAddAreas() != null && params.getAddAreas().size() > 0) {
            for (Room room : params.getAddAreas()) {
                addAndMinusArea(0, room.getLong() + "*" + room.getWidth());
            }
        }
        if (params.getMinuAreas() != null && params.getMinuAreas().size() > 0) {
            for (Room room : params.getMinuAreas()) {
                addAndMinusArea(1, room.getLong() + "*" + room.getWidth());
            }
        }

    }

    private void checkInput() {
        String roomName = mEtBedroom.getText().toString().trim();
        String roomLong = mEtLong.getText().toString().trim();
        String roomWidth = mEtWidth.getText().toString().trim();
        getAddArea();
        mRooms.clear();
        if (TextUtils.isEmpty(roomName)) {
            LUtils.toast("房间名不能为空");
            return;
        }
        if (TextUtils.isEmpty(roomLong) || !checkSize(roomLong)) {
            LUtils.toast("长度不能小于60");
            return;
        }
        if (TextUtils.isEmpty(roomWidth) || !checkSize(roomWidth)) {
            LUtils.toast("宽度不能小于60");
            return;
        }
        addRoom(roomLong, roomWidth);
        if (mLlAdd.getChildCount() > 0) {
            for (int i = 0; i < mLlAdd.getChildCount(); i++) {
                View view = mLlAdd.getChildAt(i);
                TextView etLong = view.findViewById(R.id.et_add_room_l);
                TextView etWidth = view.findViewById(R.id.et_add_room_w);
                String sLong = etLong.getText().toString().trim();
                String width = etWidth.getText().toString().trim();
                if (!TextUtils.isEmpty(sLong) && !TextUtils.isEmpty(width)) {
                    if (!checkSize(sLong)) {
                        LUtils.toast("长度不能小于60");
                        return;
                    }
                    if (!checkSize(width)) {
                        LUtils.toast("宽度不能小于60");
                        return;
                    }
                    addRoom(sLong, width);
                }
            }
        }

        getPresenter().saveParams(mAddAreas, mMinuAreas, roomName, mRooms, mRbSteady.isChecked() ? 1 : 0);
    }

    private boolean checkSize(String sizeStr) {
        if (TextUtils.isDigitsOnly(sizeStr)) {
            int size = Integer.valueOf(sizeStr);
            if (size < 60) return false;
        } else {
            return false;
        }
        return true;
    }

    private void addRoomLayout(String sLong, String width) {
        View view = getLayoutInflater().inflate(R.layout.item_add_room, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mLlAdd.addView(view);

        ImageView ivDelete = view.findViewById(R.id.iv_add_room_delete);
        ivDelete.setOnClickListener(view1 -> {
            mLlAdd.removeView(view);
        });

        TextView tvW = view.findViewById(R.id.tv_add_room_width);
        String letter = LETTERS[mLlAdd.indexOfChild(view)];
        tvW.setText(letter + getString(R.string.text_room_width));
        TextView tvH = view.findViewById(R.id.tv_add_room_height);
        tvH.setText(letter + getString(R.string.text_room_height));

        if (!TextUtils.isEmpty(sLong)) {
            TextView etLong = view.findViewById(R.id.et_add_room_l);
            etLong.setText(sLong);
        }
        if (!TextUtils.isEmpty(width)) {
            TextView etWidth = view.findViewById(R.id.et_add_room_w);
            etWidth.setText(width);
        }
    }

    private void addRoom(String sLong, String width) {
        Room room = new Room();
        room.setLong(sLong);
        room.setWidth(width);
        mRooms.add(room);
    }

    @Override
    public int[] getHideSoftViewIds() {
        return new int[]{R.id.et_room_param_bedroom, R.id.tv_room_param_extra,
                R.id.et_room_param_long, R.id.et_room_param_width};
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_room_params_steady:
                mIbtAdd.setVisibility(View.GONE);
                mTvWidth.setText(getString(R.string.text_room_width));
                mTvHeight.setText(getString(R.string.text_room_height));
                mLlAdd.removeAllViews();
                break;
            case R.id.rb_room_params_unsteady:
                mIbtAdd.setVisibility(View.VISIBLE);
                mTvWidth.setText("A" + getString(R.string.text_room_width));
                mTvHeight.setText("A" + getString(R.string.text_room_height));
                break;
        }
    }

    private void addAndMinusArea(int type, String area) {
        View view = View.inflate(this, R.layout.item_room_params_extra, null);
        TextView label = view.findViewById(R.id.tv_room_params_extra);
        TextView tvArea = view.findViewById(R.id.tv_room_param_extra);
        ImageView ivDelete = view.findViewById(R.id.iv_extra_area_delete);
        label.setText(type == 0 ? "增加面积" : "减少面积");
        if (!TextUtils.isEmpty(area)) tvArea.setText(area);
        mLlExtra.addView(view);
        ivDelete.setOnClickListener(v -> {
            mLlExtra.removeView(view);
        });
    }

    private void getAddArea() {
        mAddAreas = new ArrayList<>();
        mMinuAreas = new ArrayList<>();

        if (mLlExtra.getChildCount() > 0) {
            for (int i = 0; i < mLlExtra.getChildCount(); i++) {
                View view = mLlExtra.getChildAt(i);
                TextView tvLabel = view.findViewById(R.id.tv_room_params_extra);
                TextView tvInput = view.findViewById(R.id.tv_room_param_extra);
                String addSize = tvInput.getText().toString().trim();

                if (!TextUtils.isEmpty(addSize)) {
                    String[] sizes = addSize.split("\\*");
                    Room room = new Room();
                    room.setLong(sizes[0]);
                    room.setWidth(sizes[1]);
                    if (tvLabel.getText().toString().trim().equals("增加面积")) {
                        mAddAreas.add(room);
                    } else {
                        mMinuAreas.add(room);
                    }
                }
            }
        }
    }

    public int getMelType() {
        return mRgMaterType0.isChecked() ? 0 : 1;
    }

    public int getFloorType() {
        return mRgFloorType0.isChecked() ? 0 : 1;
    }

}
