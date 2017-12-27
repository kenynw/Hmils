package com.cube.hmils.base;

import android.app.Application;

import com.cube.hmils.utils.LUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Carol on 2017/10/29.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LUtils.initialize(this);
        Fresco.initialize(this);
    }

}
