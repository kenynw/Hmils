package com.cube.hmils.module.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cube.hmils.R;
import com.cube.hmils.app.constant.ARouterPaths;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(LoginPresenter.class)
@Route(path = ARouterPaths.ACCOUNT_LOGIN)
public class LoginActivity extends ChainBaseActivity<LoginPresenter> implements TextWatcher {

    @BindView(R.id.et_login_username)
    EditText mEtUsername;

    @BindView(R.id.et_login_password)
    EditText mEtPassword;

    @BindView(R.id.iv_login_visibility)
    ImageView mIvVisibility;

    @BindView(R.id.btn_login_login)
    Button mBtnLogin;

    @BindView(R.id.tv_login_forgot)
    TextView mTvForgot;

    private boolean mIsVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_login);
        ButterKnife.bind(this);

        new UserTextWatcher(mBtnLogin, mEtUsername, mEtPassword);
        mEtPassword.addTextChangedListener(this);
        mIvVisibility.setOnClickListener(v -> {
            if (mIsVisibility) {
                mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mIsVisibility = false;
            } else {
                mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mIsVisibility = true;
            }
        });
        mTvForgot.setOnClickListener(v -> startActivity(new Intent(this, ForgotActivity.class)));
        mBtnLogin.setOnClickListener(v -> {
            getExpansionDelegate().showProgressBar();
            getPresenter().login(mEtUsername.getText().toString().trim(), mEtPassword.getText().toString().trim());
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mIvVisibility.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
