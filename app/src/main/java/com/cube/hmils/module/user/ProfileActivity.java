package com.cube.hmils.module.user;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.User;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ProfilePresenter.class)
public class ProfileActivity extends ChainBaseActivity<ProfilePresenter> {

    public static final int REQUEST_CODE = 0x034;

    @BindView(R.id.dv_profile_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.et_profile_full_name)
    TextView mEtFullName;

    @BindView(R.id.et_profile_phone)
    TextView mEtPhone;

    @BindView(R.id.et_profile_address)
    TextView mEtAddress;

    @BindView(R.id.et_profile_cooperation)
    TextView mEtCooperation;

    @BindView(R.id.fl_profile_address)
    FrameLayout mFlAddress;

    @BindView(R.id.fl_profile_cooperation)
    FrameLayout mFlCooperation;

    @BindView(R.id.btn_profile_save)
    Button mBtnSave;

    @BindView(R.id.fl_profile_avatar)
    FrameLayout mFlProfileAvatar;

    private String mPirPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        setToolbarTitle(R.string.text_profile);
        ButterKnife.bind(this);

        mFlAddress.setVisibility(View.GONE);
        mFlCooperation.setVisibility(View.GONE);
        mFlProfileAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    public void setProfile(User user) {
        mDvAvatar.setImageURI(user.getCustImg());
        mEtFullName.setText(user.getUserName());
        mEtPhone.setText(user.getTelPhone());
        mBtnSave.setOnClickListener(v -> {
            checkProfile();
        });
    }

    public void setClientInfo(Client client) {
        mEtFullName.setText(client.getCustName());
        mEtPhone.setText(client.getPhoneNo());
        mFlAddress.setVisibility(View.VISIBLE);
        mEtAddress.setText(client.getFullAddress());
        mFlAddress.setOnClickListener(v -> startActivity(new Intent(this, EditAddressActivity.class)));
        mFlCooperation.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(client.getCreatTime()))
            mEtCooperation.setText(client.getCreatTime().substring(0, 11));
        mBtnSave.setOnClickListener(v -> startActivity(new Intent(this, ClientDetailActivity.class)));
    }

    private void checkProfile() {
        String username = mEtFullName.getText().toString().trim();
        String mobile = mEtPhone.getText().toString().trim();
        getPresenter().save(mPirPath, username, mobile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode && null != data) {
            Uri selectImageUri = data.getData();
            String[] filePathColumn = new String[]{MediaStore.Images.Media.DATA};//要查询的列
            Cursor cursor = getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
            mPirPath = null;
            while (cursor.moveToNext()) {
                mPirPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));//所选择的图片路径
            }
            cursor.close();
        }
    }
}
