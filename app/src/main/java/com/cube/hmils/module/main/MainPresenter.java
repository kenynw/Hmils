package com.cube.hmils.module.main;

import android.os.Bundle;

import com.cube.hmils.model.event.LogoutEvent;
import com.dsk.chain.bijection.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Carol on 2017/10/11.
 */

public class MainPresenter extends Presenter<MainActivity> {

    @Override
    protected void onCreate(MainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onLogout(LogoutEvent logoutEvent) {
        getView().setCurrentItem(0);
    }

}
