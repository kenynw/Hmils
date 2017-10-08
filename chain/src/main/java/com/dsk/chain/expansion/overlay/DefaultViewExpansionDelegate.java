package com.dsk.chain.expansion.overlay;

import android.app.ProgressDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dsk.chain.R;
import com.dsk.chain.bijection.ChainBaseActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class DefaultViewExpansionDelegate extends ViewExpansionDelegate {

    private ProgressDialog mProgressDialog;

    private TextView mTvEmpty;

    private View mContent;

    public DefaultViewExpansionDelegate(ChainBaseActivity activity) {
        super(activity);
    }

    @Override
    public void showProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mContent = getContainer().findViewById(R.id.layout_content);
        if (mContent != null) mContent.setVisibility(View.INVISIBLE);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("请稍等");
        mProgressDialog.show();
    }

    @Override
    public void showProgressBar(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mContent = getContainer().findViewById(R.id.layout_content);
        if (mContent != null) mContent.setVisibility(View.INVISIBLE);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
        if (mContent != null) mContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPage() {
        super.showErrorPage();
    }

    @Override
    public void showErrorToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideErrorPage() {
        super.hideErrorPage();
    }

    @Override
    public void showEmptyPage() {
        if (mTvEmpty == null) {
            mTvEmpty = new TextView(getActivity());
            mTvEmpty.setText(R.string.text_empty);
            mTvEmpty.setTextSize(R.dimen.text_size_body_material);
            getContainer().addView(mTvEmpty, getLayoutParams());
        } else {
            mTvEmpty.setVisibility(View.VISIBLE);
        }
    }

    private FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

}
