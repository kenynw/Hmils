package com.cube.hmils.module.account;

import android.content.Intent;

import com.cube.hmils.model.AccountModel;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/29.
 */

public class ForgotPresenter extends Presenter<ForgotActivity> {

    public void sendCaptcha(String mobile) {
        AccountModel.getInstance().sendCode(mobile).subscribe(new ServicesResponse<Response>() {
            @Override
            public void onNext(Response response) {
                LUtils.toast(response.getMessage());
                getView().mBtnCaptcha.startTickWork();
            }
        });
    }

    public void checkCaptcha(String mobile, String code) {
        AccountModel.getInstance().checkCode(mobile, code).subscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                getView().startActivity(new Intent(getView(), ResetPwdActivity.class));
                getView().finish();
            }
        });
    }

}
