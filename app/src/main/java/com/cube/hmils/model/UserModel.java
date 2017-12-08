package com.cube.hmils.model;

import android.text.TextUtils;

import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.local.UserPreferences;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.model.services.ServicesClient;
import com.dsk.chain.model.AbsModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Carol on 2017/11/10.
 */

public class UserModel extends AbsModel {

    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    public Observable<Response> getMessage() {
        return ServicesClient.getServices().getMessage().compose(new DefaultTransform<>());
    }

    public Observable<User> doLogin(String mobile, String password) {
        return ServicesClient.getServices().login(mobile, password)
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

    public Observable<Response> saveProfile(File file, String userName, String mobile) {
        Map<String, RequestBody> params = new HashMap<>();
        if (file != null) {
            RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            params.put("image\"; filename=\"" + file.getName() + "\"", photo);
        }
        params.put("userId", RequestBody.create(null, UserPreferences.getUserID() + ""));
        params.put("userName", RequestBody.create(null, userName));
        params.put("telPhone", RequestBody.create(null, mobile));

        return ServicesClient.getServices().editUserInfo(params).compose(new DefaultTransform<>());
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(UserPreferences.getToken()) && UserPreferences.getUserID() > 0;
    }

    private void saveAccount(User user) {
        UserPreferences.setUserID(user.getUserId());
        UserPreferences.setAgentID(user.getAgentId());
        UserPreferences.setToken(user.getToken());
    }

}
