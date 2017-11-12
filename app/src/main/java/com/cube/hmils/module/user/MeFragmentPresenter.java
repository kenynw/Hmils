package com.cube.hmils.module.user;

import com.cube.hmils.model.AccountModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.event.LogoutEvent;
import com.cube.hmils.model.local.UserPreferences;
import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carol on 2017/10/29.
 */

public class MeFragmentPresenter extends BaseDataFragmentPresenter<MeFragment, User> {

    public void logout() {
        UserPreferences.setToken("");
        UserPreferences.setUserID(0);
        UserPreferences.setAgentID(0);
        if (!AccountModel.getInstance().isLogin()) {
            EventBus.getDefault().post(new LogoutEvent());
        }
    }

}
