package com.cube.hmils.module.account;

import com.cube.hmils.model.AccountModel;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/28.
 */

public class ResetPwdPresenter extends Presenter<ResetPwdActivity> {

    public void changePwd(String mobile) {
        AccountModel.getInstance().changePwd(mobile).subscribe(new ServicesResponse<Response>() {
            @Override
            public void onNext(Response response) {
                LUtils.toast(response.getMessage());
                getView().finish();
            }
        });
    }

}
