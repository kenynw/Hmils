package com.cube.hmils.model;

import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.local.DaoSharedPreferences;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.model.services.ServicesClient;
import com.cube.hmils.utils.LUtils;
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
        return ServicesClient.getServices().login(mobile, LUtils.md5(password))
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
        User user = UserModel.getInstance().getUser();
        return ServicesClient.getServices().changePwd(user == null ? 0 : user.getUserId(), mobile).compose(new DefaultTransform<>());
    }

    /**
     * 个人中心信息
     * @return
     */
    public Observable<User> getUserDetail() {
        User user = UserModel.getInstance().getUser();
        return ServicesClient.getServices().userDetail(user == null ? 0 : user.getUserId()).compose(new DefaultTransform<>());
    }

    public Observable<Response> saveProfile(File file, String userName, String mobile) {
        Map<String, RequestBody> params = new HashMap<>();
        if (file != null) {
            RequestBody photo = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            params.put("image\"; filename=\"" + file.getName() + "\"", photo);
        }
        User user = UserModel.getInstance().getUser();
        params.put("userId", RequestBody.create(null, user == null ? "" : user.getUserId() + ""));
        params.put("userName", RequestBody.create(null, userName));
        params.put("telPhone", RequestBody.create(null, mobile));

        return ServicesClient.getServices().editUserInfo(params).compose(new DefaultTransform<>());
    }

    public User getUser() {
        return DaoSharedPreferences.getInstance().getUser();
    }

    public void setUser(User user) {
        DaoSharedPreferences.getInstance().setUser(user);
    }

    public void logout() {
        DaoSharedPreferences.getInstance().clearUser();
    }

    public boolean isLogin() {
        return getUser() != null;
    }

    private void saveAccount(User user) {
        DaoSharedPreferences.getInstance().setUser(user);
    }

}
