package com.cube.hmils.module.account;

import android.content.Intent;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.EventBusUtil;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/28.
 */

public class LoginPresenter extends Presenter<LoginActivity> {

    public void login(String mobile, String password) {
        UserModel.getInstance().doLogin(mobile, password).unsafeSubscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                getView().getExpansionDelegate().hideProgressBar();
                if (user.getFirstLogin() == 0) {
                    getView().startActivity(new Intent(getView(), ResetPwdActivity.class));
                } else {
                    EventBusUtil.eventPost(EventCode.ORDER_LIST_UPDATE);
                    getView().finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().getExpansionDelegate().hideProgressBar();
            }
        });
    }

}
