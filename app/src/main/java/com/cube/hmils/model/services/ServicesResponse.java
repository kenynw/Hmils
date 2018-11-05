package com.cube.hmils.model.services;

import android.content.Intent;

import com.cube.hmils.app.base.App;
import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.constant.HmilsIntent;
import com.cube.hmils.utils.LUtils;

import rx.Subscriber;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class ServicesResponse<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ServiceException) {
            serviceError((ServiceException) e);
        } else {
            serviceError(new ServiceException(-1, "网络错误"));
        }
    }

    @Override
    public void onNext(T t) {

    }

    private void serviceError(ServiceException e) {
        LUtils.toast(e.getMsg());
        if (e.getCode() == 10094) {
            UserModel.getInstance().logout();
            Intent intent = new Intent(HmilsIntent.INTENT_ACTION_LOGIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getInstance().startActivity(intent);
        }
    }

}
