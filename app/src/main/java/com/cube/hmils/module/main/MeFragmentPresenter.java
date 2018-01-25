package com.cube.hmils.module.main;

import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.module.account.LoginActivity;
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

    public void loadUser() {
        UserModel.getInstance().getUserDetail().unsafeSubscribe(getSubscriber());
    }

    public void logout() {
        UserModel.getInstance().logout();
        if (!UserModel.getInstance().isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putInt(EVENT_BUS_CODE, EventCode.LOGOUT);
            EventBus.getDefault().post(bundle);
            getView().startActivity(new Intent(getView().getActivity(), LoginActivity.class));
        }
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.CODE_ME_UPDATE) {
            loadUser();
        }
    }

}
