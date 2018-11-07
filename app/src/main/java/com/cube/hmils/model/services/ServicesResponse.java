package com.cube.hmils.model.services;

import com.cube.hmils.app.Navigator;
import com.cube.hmils.model.UserModel;
import com.cube.hmils.utils.LUtils;

import rx.Subscriber;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public abstract class ServicesResponse<T> extends Subscriber<T> {

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

    private void serviceError(ServiceException e) {
        LUtils.toast(e.getMsg());
        if (e.getCode() == 10094) {
            UserModel.getInstance().logout();
            Navigator.getInstance().openLoginActivity();
        }
        onError(e.getCode(), e.getMsg());
    }

    @Override
    public abstract void onNext(T t);

    /**
     * Desc: 具体业务各自处理
     * <p>
     * Author: 廖培坤
     * Date: 2018-07-04
     *
     * @param code
     * @param msg
     */
    public void onError(int code, String msg) {

    }

}
