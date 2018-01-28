package com.cube.hmils.module.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.utils.LUtils;


/**
 * Copyright (c) 2017/7/24. LiaoPeiKun Inc. All rights reserved.
 */

public class ExtraAreaDialog extends DialogFragment implements View.OnClickListener {

    private EditText mEtWidth;
    private EditText mEtHeight;

    public interface OnDialogShow {
        void onShow(View view);
    }

    public static ExtraAreaDialog newInstance(int type) {
        ExtraAreaDialog dialog = new ExtraAreaDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        dialog.setArguments(bundle);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_area, null);

        if (getArguments() != null) {
            int type = getArguments().getInt("type");
            TextView tvTitle = view.findViewById(R.id.tv_add_area_title);
            tvTitle.setText(type == 0 ? R.string.text_add_area : R.string.text_minus_area);
        }
        mEtWidth = view.findViewById(R.id.et_add_area_width);
        mEtHeight = view.findViewById(R.id.et_add_area_height);

        TextView btnNegative = view.findViewById(R.id.btn_dialog_negative);
        if (btnNegative != null) {
            btnNegative.setOnClickListener(this);
        }
        TextView btnPositive = view.findViewById(R.id.btn_dialog_positive);
        if (btnPositive != null) {
            btnPositive.setOnClickListener(this);
        }

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (getTargetFragment() instanceof OnDialogShow) {
                    ((OnDialogShow) getTargetFragment()).onShow(view);
                } else if (getActivity() instanceof OnDialogShow){
                    ((OnDialogShow) getActivity()).onShow(view);
                }
            }
        });

        if (dialog.getWindow() != null) dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout((int) (LUtils.getScreenWidth() * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    @Override
    public void onClick(View view) {
        DialogCallback targetFragment = null;
        if (getTargetFragment() != null && getTargetFragment() instanceof DialogCallback) {
            targetFragment = (DialogCallback) getTargetFragment();
        } else if (getActivity() instanceof DialogCallback) {
            targetFragment = (DialogCallback) getActivity();
        }
        if (targetFragment != null) {
            if (view.getId() == R.id.btn_dialog_positive) {
                if (TextUtils.isEmpty(mEtWidth.getText().toString().trim())) {
                    LUtils.toast("请输入房间长度");
                    return;
                }
                if (TextUtils.isEmpty(mEtHeight.getText().toString().trim())) {
                    LUtils.toast("请输入房间宽度");
                    return;
                }

                targetFragment.onPositiveClick(view);
            }
            else if (view.getId() == R.id.btn_dialog_negative) {
                targetFragment.onNegativeClick(view);
            }
        }
        dismiss();
    }

}
