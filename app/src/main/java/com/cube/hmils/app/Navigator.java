package com.cube.hmils.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cube.hmils.app.constant.ARouterPaths;
import com.cube.hmils.model.constant.ExtraConstant;

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

    public void openCreateOrderActivity(String projectId) {
        ARouter.getInstance().build(ARouterPaths.CREATE_ORDER)
                .withString(ExtraConstant.EXTRA_PROJECT_ID, projectId)
                .navigation();
    }

}
