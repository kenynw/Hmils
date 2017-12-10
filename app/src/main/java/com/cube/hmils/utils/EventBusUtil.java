package com.cube.hmils.utils;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import static com.dsk.chain.bijection.Presenter.EVENT_BUS_CODE;

/**
 * Created by Carol on 2017/12/11.
 */

public class EventBusUtil {

    public static void eventPost(int code) {
        Bundle bundle = new Bundle();
        bundle.putInt(EVENT_BUS_CODE, code);
        EventBus.getDefault().post(bundle);
    }

    public static void eventPost(int code, Bundle bundle) {
        bundle.putInt(EVENT_BUS_CODE, code);
        EventBus.getDefault().post(bundle);
    }

}
