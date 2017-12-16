package com.cube.hmils.module.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(ResetPwdPresenter.class)
public class ResetPwdActivity extends ChainBaseActivity<ResetPwdPresenter> implements TextWatcher,
        CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.et_forgot_password)
    EditText mEtPwd;

    @BindView(R.id.et_forgot_confirm)
    EditText mEtConfirm;

    @BindView(R.id.tv_forgot_error_pwd)
    TextView mTvErrorPwd;

    @BindView(R.id.cb_forgot_pwd)
    CheckBox mCbPwd;

    @BindView(R.id.cb_forgot_confirm)
    CheckBox mCbConfirm;

    @BindView(R.id.btn_forgot_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_reset);
        ButterKnife.bind(this);

        new UserTextWatcher(mBtnSave, mEtPwd, mEtConfirm);
        mCbPwd.setOnCheckedChangeListener(this);
        mCbConfirm.setOnCheckedChangeListener(this);
        mEtPwd.addTextChangedListener(this);
        mEtConfirm.addTextChangedListener(this);
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void checkInput() {
        String pwdText = mEtPwd.getText().toString().trim();
        String pwdConfirm = mEtConfirm.getText().toString().trim();
        if (!TextUtils.isEmpty(pwdText) && pwdText.equals("123456")) {
            mTvErrorPwd.setVisibility(View.VISIBLE);
            return;
        }
        if (!pwdText.equals(pwdConfirm)) {
            LUtils.toast("两次输入的密码不一样");
            return;
        }
        getPresenter().changePwd(mEtPwd.getText().toString().trim());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mCbPwd.setVisibility(mEtPwd.getText().length() > 0? View.VISIBLE : View.GONE);
        mCbConfirm.setVisibility(mEtConfirm.getText().length() > 0? View.VISIBLE : View.GONE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        EditText editText = buttonView.getId() == R.id.cb_forgot_pwd ? mEtPwd : mEtConfirm;
        if (isChecked) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        editText.setSelection(editText.getText().length());
    }

}
