package com.cube.hmils.module.main;

import android.os.Bundle;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
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
            Bundle bundle = new Bundle();
            bundle.putInt(EVENT_BUS_CODE, EventCode.LOGOUT);
            EventBus.getDefault().post(bundle);
        }
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.CODE_ME_UPDATE) {
            loadUser();
        }
    }

}
