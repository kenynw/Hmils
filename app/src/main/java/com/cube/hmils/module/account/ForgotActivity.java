package com.cube.hmils.module.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotActivity extends ChainBaseActivity<ForgotPresenter> implements TextWatcher {

    @BindView(R.id.et_forgot_password)
    EditText mEtUsername;

    @BindView(R.id.et_forgot_confirm)
    EditText mEtPassword;

    @BindView(R.id.tv_forgot_error_pwd)
    TextView mTvErrorPwd;

    @BindView(R.id.iv_forgot_visibility)
    ImageView mIvVisibility;

    @BindView(R.id.btn_forgot_save)
    Button mBtnSave;

    private boolean mIsVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_forgot);
        ButterKnife.bind(this);

        new UserTextWatcher(mBtnSave, mEtUsername, mEtPassword);
        mIvVisibility.setOnClickListener(v -> {
            if (mIsVisibility) {
                mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mIsVisibility = false;
            } else {
                mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mIsVisibility = true;
            }
        });
        mEtPassword.addTextChangedListener(this);
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void checkInput() {
        String pwdText = mEtPassword.getText().toString().trim();
        if (!TextUtils.isEmpty(pwdText) && pwdText.equals("123456")) {
            mTvErrorPwd.setVisibility(View.VISIBLE);
        }
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
