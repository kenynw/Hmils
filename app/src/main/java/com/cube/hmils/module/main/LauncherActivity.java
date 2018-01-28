package com.cube.hmils.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dsk.chain.bijection.ChainBaseActivity;

public class LauncherActivity extends ChainBaseActivity<LauncherPresenter> {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }, 2000);
    }

}
