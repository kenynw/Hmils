package com.cube.hmils.module.account;

import android.os.Bundle;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.constant.ExtraConstant;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/28.
 */

public class ResetPwdPresenter extends Presenter<ResetPwdActivity> {

    private int mUserId;

    @Override
    protected void onCreate(ResetPwdActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUserId = view.getIntent().getIntExtra(ExtraConstant.EXTRA_USER_ID, 0);
    }

    public void changePwd(String pwd) {
        UserModel.getInstance().changePwd(mUserId, pwd).subscribe(new ServicesResponse<Response>() {
            @Override
            public void onNext(Response response) {
                LUtils.toast(response.getMessage());
                getView().finish();
            }
        });
    }

}
