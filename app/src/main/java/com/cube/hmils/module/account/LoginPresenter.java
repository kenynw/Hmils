package com.cube.hmils.module.account;

import android.os.Bundle;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carol on 2017/10/28.
 */

public class LoginPresenter extends Presenter<LoginActivity> {

    public void login(String mobile, String password) {
        UserModel.getInstance().doLogin(mobile, password).unsafeSubscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                Bundle bundle = new Bundle();
                bundle.putInt(EVENT_BUS_CODE, EventCode.ORDER_LIST_UPDATE);
                EventBus.getDefault().post(bundle);
                getView().getExpansionDelegate().hideProgressBar();
                getView().finish();
            }
        });
    }

}
