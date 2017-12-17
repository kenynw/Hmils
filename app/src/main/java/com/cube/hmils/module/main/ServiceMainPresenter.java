package com.cube.hmils.module.main;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.Service;
import com.cube.hmils.model.bean.OrderResponse;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

import java.util.ArrayList;

/**
 * Created by Carol on 2017/10/11.
 */

public class ServiceMainPresenter extends Presenter<ServiceMainFragment> {

    @Override
    protected void onCreateView(ServiceMainFragment view) {
        super.onCreateView(view);
        load();
    }

    private void load() {
        ClientModel.getInstance().getServiceList().map(OrderResponse::getOrderList)
                .unsafeSubscribe(new ServicesResponse<ArrayList<Service>>() {
                    @Override
                    public void onNext(ArrayList<Service> list) {
                        getView().initTabs(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
