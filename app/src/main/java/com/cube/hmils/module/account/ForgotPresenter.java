package com.cube.hmils.module.account;

import com.cube.hmils.model.UserModel;
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
        UserModel.getInstance().sendCode(mobile).subscribe(new ServicesResponse<Response>() {
            @Override
            public void onNext(Response response) {
                LUtils.toast(response.getMessage());
                getView().mBtnCaptcha.startTickWork();
            }
        });
    }

    public void checkCaptcha(String mobile, String code) {
        UserModel.getInstance().checkCode(mobile, code).subscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                ResetPwdActivity.start(getView(), user.getUserId());
                getView().finish();
            }
        });
    }

}
