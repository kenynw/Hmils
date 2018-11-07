package com.cube.hmils.module.order;

import com.blankj.utilcode.util.ToastUtils;
import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.RoomOrderList;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

public class CreateOrderPresenter extends Presenter<CreateOrderActivity> {

    public void submit(int packageType, int payType, String projectId) {
        if (packageType < 0) {
            ToastUtils.showLong("请选择套餐");
            return;
        }
        ClientModel.getInstance().choosePackage(packageType, payType, projectId)
                .subscribe(new ServicesResponse<RoomOrderList>() {
                    @Override
                    public void onNext(RoomOrderList roomOrderRes) {
                        getView().finish();
                    }
                });
    }

}
