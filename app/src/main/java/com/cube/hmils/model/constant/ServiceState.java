package com.cube.hmils.model.constant;

/**
 * Created by Carol on 2017/12/18.
 */

public enum  ServiceState {

    HANDED(0, 6801, "未处理"),// 未处理

    HANDING(1, 6802, "处理中") , // 处理中

    PENDING(2, 6803, "已完成") ; // 已完成

    public final int mIndex;

    public final int mState;

    public final String mText;

    ServiceState(int index, int state, String text) {
        mIndex = index;
        mState = state;
        mText = text;
    }

    public static ServiceState stateFromState(int state) {
        for (ServiceState serviceState : ServiceState.values()) {
            if (serviceState.mState == state) return serviceState;
        }
        return null;
    }

}
