package com.cube.hmils.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.module.account.ForgotActivity;
import com.cube.hmils.module.dialog.DialogCallback;
import com.cube.hmils.module.user.ProfileActivity;
import com.cube.hmils.module.user.ProfilePresenter;
import com.cube.hmils.module.user.QRCodeActivity;
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
public class MeFragment extends BaseDataFragment<MeFragmentPresenter, User> implements DialogCallback {

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

    @BindView(R.id.tv_me_logout)
    TextView mTvLogout;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_me, null);
        unbinder = ButterKnife.bind(this, view);

        mTvLogout.setOnClickListener(v -> showLogoutDialog());
        mTvResetPwd.setOnClickListener(v -> startActivity(new Intent(getActivity(), ForgotActivity.class)));

        return view;
    }

    @Override
    public void setData(User user) {
        mTvName.setText(user.getUserName());
        mTvPhone.setText(user.getTelPhone());
        mClProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            intent.putExtra(ProfilePresenter.EXTRA_USER, user);
            startActivity(intent);
        });
        mTvQrCode.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), QRCodeActivity.class);
            intent.putExtra("url", user.getQRcode());
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showLogoutDialog() {
        View view = View.inflate(getActivity(), R.layout.dialog_order_confirm, null);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).show();
        TextView tvMessage = view.findViewById(R.id.tv_dialog_message);
        tvMessage.setText("确认退出");
        view.findViewById(R.id.btn_dialog_positive).setOnClickListener(v -> {
            dialog.dismiss();
            getPresenter().logout();
        });

        view.findViewById(R.id.btn_dialog_negative).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public void onPositiveClick(@NonNull View view) {
        if (getChildFragmentManager().findFragmentByTag("confirm") != null) {
        }
    }

    @Override
    public void onNegativeClick(@NonNull View view) {

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            if (menuVisible) {
                getView().setVisibility(View.VISIBLE);
                getPresenter().loadUser();
            } else {
                getView().setVisibility(View.INVISIBLE);
            }
        }
    }

}
