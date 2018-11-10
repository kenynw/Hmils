package com.cube.hmils.app;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cube.hmils.app.constant.ARouterPaths;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.constant.ExtraConstant;
import com.cube.hmils.utils.EventBusUtil;

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

    public void toHomeActivity(Context context) {
        ARouter.getInstance().build(ARouterPaths.MAIN)
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .navigation(context, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        EventBusUtil.eventPost(EventCode.TO_HOME);
                    }
                });
    }

    public void openLoginActivity() {
        ARouter.getInstance().build(ARouterPaths.ACCOUNT_LOGIN).navigation();
    }

    /**
     * 打开选择套餐
     *
     * @param projectId
     */
    public void openCreateOrderActivity(String projectId) {
        ARouter.getInstance().build(ARouterPaths.CREATE_ORDER)
                .withString(ExtraConstant.EXTRA_PROJECT_ID, projectId)
                .navigation();
    }

    /**
     * 打开输入房间数量
     *
     * @param projectId
     */
    public void openRoomNumActivity(String projectId, String clientName) {
        ARouter.getInstance().build(ARouterPaths.ROOM_NUM)
                .withString(ExtraConstant.EXTRA_PROJECT_ID, projectId)
                .withString(ExtraConstant.CLIENT_NAME, clientName)
                .navigation();
    }

}
