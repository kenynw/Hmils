package com.cube.hmils.module.user;

import android.os.Bundle;

import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/29.
 */

public class QRCodePresenter extends Presenter<QRCodeActivity> {

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.CODE_FINISH) {
            getView().finish();
        }
    }

}
