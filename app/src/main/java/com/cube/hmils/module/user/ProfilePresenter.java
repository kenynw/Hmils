package com.cube.hmils.module.user;

import android.os.Bundle;

import com.cube.hmils.model.bean.Client;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/29.
 */

public class ProfilePresenter extends Presenter<ProfileActivity> {

    public static final String EXTRA_CLIENT = "client"; // 完善客户资料

    private Client mClient;

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mClient = getView().getIntent().getParcelableExtra(EXTRA_CLIENT);
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        if (mClient != null) {
            getView().setData(mClient);
        }
    }

}
