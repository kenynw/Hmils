package com.cube.hmils.module.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cube.hmils.R;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ForgotPresenter.class)
public class ForgotActivity extends ChainBaseActivity<ForgotPresenter> implements TextWatcher {

    @BindView(R.id.et_forgot_phone)
    EditText mEtPhone;

    @BindView(R.id.iv_forgot_visibility)
    ImageView mIvVisibility;

    @BindView(R.id.et_forgot_captcha)
    EditText mEtCaptcha;

    @BindView(R.id.btn_forgot_captcha)
    Button mBtnCaptcha;

    @BindView(R.id.btn_forgot_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_forgot);
        ButterKnife.bind(this);

        mEtPhone.addTextChangedListener(this);
        new UserTextWatcher(mBtnSave, mEtPhone, mEtCaptcha);
        mBtnSave.setOnClickListener(v -> startActivity(new Intent(this, ResetPwdActivity.class)));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mBtnCaptcha.setEnabled(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
