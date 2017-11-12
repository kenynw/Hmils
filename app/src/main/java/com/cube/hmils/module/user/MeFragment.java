package com.cube.hmils.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.User;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Carol on 2017/10/29.
 */
@RequiresPresenter(MeFragmentPresenter.class)
public class MeFragment extends BaseDataFragment<MeFragmentPresenter, User> {

    @BindView(R.id.cl_me_profile)
    ConstraintLayout mClProfile;

    @BindView(R.id.dv_me_avatar)
    SimpleDraweeView mDvAvatar;

    @BindView(R.id.tv_me_name)
    TextView mTvName;

    @BindView(R.id.tv_me_phone)
    TextView mTvPhone;

    @BindView(R.id.tv_me_qr_code)
    TextView mTvQrCode;

    @BindView(R.id.tv_me_reset_pwd)
    TextView mTvResetPwd;
    Unbinder unbinder;

    @BindView(R.id.tv_me_logout)
    TextView mTvLogout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_me, null);
        unbinder = ButterKnife.bind(this, view);

        mClProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProfileActivity.class)));
        mTvQrCode.setOnClickListener(v -> startActivity(new Intent(getActivity(), QRCodeActivity.class)));
        mTvLogout.setOnClickListener(v -> getPresenter().logout());

        return view;
    }

    @Override
    public void setData(User user) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            if (menuVisible) {
                getView().setVisibility(View.VISIBLE);
            } else {
                getView().setVisibility(View.INVISIBLE);
            }
        }
    }

}
