package com.cube.hmils.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cube.hmils.app.constant.ARouterPaths;

public class Navigator {

    private static Navigator sInstance;

    public static Navigator getInstance() {
        if (null == sInstance) {
            synchronized (Navigator.class) {
                if (null == sInstance) {
                    sInstance = new Navigator();
                }
            }
        }
        return sInstance;
    }

    public void openLoginActivity() {
        ARouter.getInstance().build(ARouterPaths.ACCOUNT_LOGIN).navigation();
    }

    public void openCreateOrderActivity() {
        ARouter.getInstance().build(ARouterPaths.CREATE_ORDER).navigation();
    }

}
