package com.cube.hmils.module.user;

import android.content.Intent;
import android.os.Bundle;
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

    @BindView(R.id.dv_profile_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.tv_profile_full_name)
    TextView mTvFullName;

    @BindView(R.id.tv_profile_phone)
    TextView mTvPhone;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        setToolbarTitle(R.string.text_profile);
        ButterKnife.bind(this);

        mFlAddress.setVisibility(View.GONE);
        mFlCooperation.setVisibility(View.GONE);
    }

    public void setProfile(User user) {
        mDvAvatar.setImageURI(user.getCustImg());
        mTvFullName.setText(user.getUserName());
        mTvPhone.setText(user.getTelPhone());
        mBtnSave.setOnClickListener(v -> {

        });
    }

    public void setClientInfo(Client client) {
        mTvFullName.setText(client.getCustName());
        mTvPhone.setText(client.getPhoneNo());
        mFlAddress.setVisibility(View.VISIBLE);
        mTvAddress.setText(client.getFullAddress());
        mFlAddress.setOnClickListener(v -> startActivity(new Intent(this, EditAddressActivity.class)));
        mFlCooperation.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(client.getCreatTime()))
            mTvCooperation.setText(client.getCreatTime().substring(0, 11));
        mBtnSave.setOnClickListener(v -> startActivity(new Intent(this, ClientDetailActivity.class)));
    }

}
