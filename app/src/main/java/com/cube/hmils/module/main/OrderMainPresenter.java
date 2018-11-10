package com.cube.hmils.module.main;

import android.os.Bundle;

import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/15.
 */

public class OrderMainPresenter extends Presenter<OrderMainFragment> {

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        switch (eventCode) {
            case EventCode.SET_ORDER_LIST:
                getView().mVpOrder.setCurrentItem(1);
                break;
        }
    }

}
