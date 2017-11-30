package com.cube.hmils.module.main;

import android.os.Bundle;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.bijection.Presenter;

import rx.Subscriber;

/**
 * Created by Carol on 2017/10/11.
 */

public class MainPresenter extends Presenter<MainActivity> {

    @Override
    protected void onCreate(MainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        UserModel.getInstance().getMessage().unsafeSubscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response response) {

            }
        });
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.LOGOUT) {
            getView().setCurrentItem(0);
        }
    }

}
