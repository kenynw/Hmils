package com.cube.hmils.model;

import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.local.UserPreferences;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.model.services.ServicesClient;
import com.dsk.chain.model.AbsModel;

import rx.Observable;

/**
 * Created by Carol on 2017/11/10.
 */

public class AccountModel extends AbsModel {

    public static AccountModel getInstance() {
        return getInstance(AccountModel.class);
    }

    public Observable<User> doLogin(String mobile, String password) {
        return ServicesClient.getServices().login(mobile, password)
                .doOnNext(this::saveAccount)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> sendCode(String mobile, String password) {
        return ServicesClient.getServices().sendCode(mobile, password).compose(new DefaultTransform<>());
    }

    private void saveAccount(User user) {
        UserPreferences.setUserID(user.getUserId());
        UserPreferences.setAgentID(user.getAgentId());
        UserPreferences.setToken(user.getToken());
    }

}
