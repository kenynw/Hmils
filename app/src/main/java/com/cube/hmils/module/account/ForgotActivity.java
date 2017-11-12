package com.cube.hmils.module.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cube.hmils.R;
import com.cube.hmils.utils.LUtils;
import com.cube.hmils.widget.SendValidateButton;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ForgotPresenter.class)
public class ForgotActivity extends ChainBaseActivity<ForgotPresenter> implements TextWatcher, SendValidateButton.SendValidateButtonListener  {

    @BindView(R.id.et_forgot_phone)
    EditText mEtPhone;

    @BindView(R.id.iv_forgot_visibility)
    ImageView mIvVisibility;

    @BindView(R.id.et_forgot_captcha)
    EditText mEtCaptcha;

    @BindView(R.id.btn_forgot_captcha)
    SendValidateButton mBtnCaptcha;

    @BindView(R.id.btn_forgot_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_forgot);
        ButterKnife.bind(this);

        mEtPhone.addTextChangedListener(this);
        new UserTextWatcher(mBtnSave, mEtPhone, mEtCaptcha);
        mBtnCaptcha.setOnClickListener(v -> checkMobile());
        mBtnCaptcha.setListener(this);
        mBtnCaptcha.setmEnableColor(getResources().getColor(R.color.white));
        mBtnCaptcha.setmEnableString("获取验证码");
        mBtnCaptcha.setmDisableColor(getResources().getColor(R.color.white));
        mBtnSave.setOnClickListener(v -> getPresenter().checkCaptcha(
                mEtPhone.getText().toString().trim(),
                mEtCaptcha.getText().toString().trim()
        ));
    }

    public void checkMobile() {
        if (mEtPhone.getText().length() <= 0) {
            LUtils.toast("请输入手机号");
            return;
        }
        getPresenter().sendCaptcha(mEtPhone.getText().toString().trim());
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

    @Override
    public void onClickSendValidateButton() {

    }

    @Override
    public void onTick() {
        mBtnCaptcha.setEnabled(!TextUtils.isEmpty(mEtPhone.getText()) && !mBtnCaptcha.isDisable());
    }

    @Override
    public void onTickStop() {

    }

}
