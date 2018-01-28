package com.cube.hmils.base;

import android.app.Application;

import com.cube.hmils.utils.LUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

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
        LUtils.isDebug = false;
        Fresco.initialize(this);
    }

}
