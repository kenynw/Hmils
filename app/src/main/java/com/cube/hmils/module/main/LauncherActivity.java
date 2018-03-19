package com.cube.hmils.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

@RequiresPresenter(LauncherPresenter.class)
public class LauncherActivity extends ChainBaseActivity<LauncherPresenter> {

    private Handler mHandler = new Handler();

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.postDelayed(mRunnable, 2000);
    }

}
