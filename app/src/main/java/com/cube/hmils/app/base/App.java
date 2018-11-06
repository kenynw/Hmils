package com.cube.hmils.app.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.Utils;
import com.cube.hmils.BuildConfig;
import com.cube.hmils.utils.LUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;

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
        Utils.init(this);
        if (!ProcessUtils.isMainProcess()) {
            return;
        }
        LUtils.initialize(this);
        LUtils.isDebug = BuildConfig.DEBUG;
        Fresco.initialize(this);
        initJPush();
        initBugly();
        initARouter();
    }

    /**
     * 初始化极光推送
     */
    private void initJPush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = LUtils.getProcessName();
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "e033e8cbf3", true, strategy);
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

}
