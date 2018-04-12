package com.cube.hmils.module.account;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServiceException;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.EventBusUtil;
import com.cube.hmils.utils.LUtils;
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
                    ResetPwdActivity.start(getView(), user.getUserId());
                } else {
                    user.setUserName(mobile);
                    UserModel.getInstance().setUser(user);
                    EventBusUtil.eventPost(EventCode.ORDER_LIST_UPDATE);
                    EventBusUtil.eventPost(EventCode.INIT_PUSH);
                    getView().finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                getView().getExpansionDelegate().hideProgressBar();
                if (e instanceof ServiceException) {
                    ServiceException exception = (ServiceException) e;
                    LUtils.toast(exception.getMsg());
                } else {
                    LUtils.toast("登录失败");
                }
            }
        });
    }

}
