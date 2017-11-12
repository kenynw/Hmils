package com.cube.hmils.module.account;

import com.cube.hmils.model.AccountModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/28.
 */

public class LoginPresenter extends Presenter<LoginActivity> {

    public void login(String mobile, String password) {
        AccountModel.getInstance().doLogin(mobile, password).unsafeSubscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                getView().getExpansionDelegate().hideProgressBar();
                getView().finish();
            }
        });
    }

}
