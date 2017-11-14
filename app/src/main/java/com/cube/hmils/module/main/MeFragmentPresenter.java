package com.cube.hmils.module.main;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.event.LogoutEvent;
import com.cube.hmils.model.local.UserPreferences;
import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carol on 2017/10/29.
 */

public class MeFragmentPresenter extends BaseDataFragmentPresenter<MeFragment, User> {

    @Override
    protected void onCreateView(MeFragment view) {
        super.onCreateView(view);
        loadUser();
    }

    private void loadUser() {
        UserModel.getInstance().getUserDetail().unsafeSubscribe(getSubscriber());
    }

    public void logout() {
        UserPreferences.setToken("");
        UserPreferences.setUserID(0);
        UserPreferences.setAgentID(0);
        if (!UserModel.getInstance().isLogin()) {
            EventBus.getDefault().post(new LogoutEvent());
        }
    }

}
