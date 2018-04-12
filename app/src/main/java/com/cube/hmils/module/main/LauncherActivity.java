package com.cube.hmils.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.just("").delay(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

    }

}
