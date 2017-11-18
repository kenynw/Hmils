package com.cube.hmils.model;

import android.text.TextUtils;

import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.local.UserPreferences;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.model.services.ServicesClient;
import com.dsk.chain.model.AbsModel;

import rx.Observable;

/**
 * Created by Carol on 2017/11/10.
 */

public class UserModel extends AbsModel {

    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    public Observable<User> doLogin(String mobile, String password) {
        return ServicesClient.getServices().login("peikun", "123456")
                .doOnNext(this::saveAccount)
                .compose(new DefaultTransform<>());
    }

    public Observable<Response> sendCode(String mobile) {
        return ServicesClient.getServices().sendCode(0, mobile).compose(new DefaultTransform<>());
    }

    public Observable<User> checkCode(String mobile, String code) {
        return ServicesClient.getServices().checkCode(mobile, code).compose(new DefaultTransform<>());
    }

    public Observable<Response> changePwd(String mobile) {
        return ServicesClient.getServices().changePwd(UserPreferences.getUserID(), mobile).compose(new DefaultTransform<>());
    }

    /**
     * 个人中心信息
     * @return
     */
    public Observable<User> getUserDetail() {
        return ServicesClient.getServices().userDetail(UserPreferences.getUserID()).compose(new DefaultTransform<>());
    }

//    public Observable<Response> editUserInfo() {
//        return ServicesClient.getServices().editUserInfo()
//    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(UserPreferences.getToken())
                && UserPreferences.getUserID() > 0 && UserPreferences.getAgentID() > 0;
    }

    private void saveAccount(User user) {
        UserPreferences.setUserID(user.getUserId());
        UserPreferences.setAgentID(user.getAgentId());
        UserPreferences.setToken(user.getToken());
    }

}
