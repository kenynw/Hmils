package com.cube.hmils.base;

import android.app.Application;

import com.cube.hmils.BuildConfig;
import com.cube.hmils.utils.LUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Carol on 2017/10/29.
 */

public class App extends Application {

    private static App mInstance;

    public static App getInstance() {
        synchronized (App.class) {
            if (mInstance == null) {
                mInstance = new App();
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LUtils.initialize(this);
        LUtils.isDebug = BuildConfig.DEBUG;
        Fresco.initialize(this);
        initJPush();
    }

    /**
     * 初始化极光推送
     */
    private void initJPush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }

}
