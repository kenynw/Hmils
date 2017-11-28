package com.cube.hmils.module.user;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.cube.hmils.R;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.utils.LUtils;
import com.cube.hmils.utils.PermissionUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ProfilePresenter.class)
public class ProfileActivity extends ChainBaseActivity<ProfilePresenter> implements OnImageSelectListener {

    @BindView(R.id.dv_profile_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.et_profile_full_name)
    TextView mEtFullName;

    @BindView(R.id.et_profile_phone)
    TextView mEtPhone;

    @BindView(R.id.tv_profile_address)
    TextView mTvAddress;

    @BindView(R.id.tv_profile_cooperation)
    TextView mTvCooperation;

    @BindView(R.id.fl_profile_address)
    FrameLayout mFlAddress;

    @BindView(R.id.fl_profile_cooperation)
    FrameLayout mFlCooperation;

    @BindView(R.id.btn_profile_save)
    Button mBtnSave;

    @BindView(R.id.fl_profile_avatar)
    FrameLayout mFlProfileAvatar;

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        setToolbarTitle(R.string.text_profile);
        ButterKnife.bind(this);

        mFlAddress.setVisibility(View.GONE);
        mFlCooperation.setVisibility(View.GONE);
        mFlProfileAvatar.setOnClickListener(v -> {
            if (!PermissionUtils.checkPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                String[] permissions = new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                };

                PermissionUtils.requestPermission(this, permissions, 100);
            } else {
                ImageProvider.getInstance(this).getImageFromCameraOrAlbum(this);
            }
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
        if (client == null) return;
        mEtFullName.setText(client.getCustName());
        mEtPhone.setText(client.getPhoneNo());
        mFlAddress.setVisibility(View.VISIBLE);
        mTvAddress.setText(client.getFullAddress());
        mFlAddress.setOnClickListener(v -> startActivity(new Intent(this, EditAddressActivity.class)));
        mFlCooperation.setVisibility(View.VISIBLE);
        mFlCooperation.setOnClickListener(v -> showPickerTime());
        if (!TextUtils.isEmpty(client.getCreatTime()))
            mTvCooperation.setText(client.getCreatTime().substring(0, 11));
        mBtnSave.setOnClickListener(v -> {
            checkClientInfo();
        });
    }

    public void setAddress(String address) {
        mTvAddress.setText(address);
    }

    private void checkProfile() {
        String username = mEtFullName.getText().toString().trim();
        String mobile = mEtPhone.getText().toString().trim();
        getPresenter().saveProfile(mUri, username, mobile);
    }

    private void checkClientInfo() {
        String name = mEtFullName.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        String address = mTvAddress.getText().toString().trim();
        String time = mTvCooperation.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            LUtils.toast("名字不能为空");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            LUtils.toast("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            LUtils.toast("地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(time)) {
            LUtils.toast("合作时间不能为空");
            return;
        }

        getPresenter().saveClient(name, phone);
    }

    private void showPickerTime() {
        new TimePickerView.Builder(this, (date, v) -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            mTvCooperation.setText(format.format(date));
        }).setType(new boolean[] {true, true, true, false, false, false})
                .setSubmitText("完成")
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))
                .setCancelColor(getResources().getColor(R.color.textSecondary))
                .setLabel("", "", "", "", "", "")
                .setDate(Calendar.getInstance())
                .setRangDate(null, Calendar.getInstance())
                .build()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageProvider.getInstance(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!PermissionUtils.checkPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("拒绝权限将会影响功能使用，请务必同意权限请求")
                    .show();
        } else {
            ImageProvider.getInstance(this).getImageFromCameraOrAlbum(this);
        }
    }

    @Override
    public void onImageSelect() {

    }

    @Override
    public void onImageLoaded(Uri uri) {
        ImageProvider.getInstance(this).corpImage(uri, 200, 200, new OnImageSelectListener() {
            @Override
            public void onImageSelect() {

            }

            @Override
            public void onImageLoaded(Uri uri) {
                mUri = uri;
                mDvAvatar.setImageURI(uri);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onError() {

    }

}
