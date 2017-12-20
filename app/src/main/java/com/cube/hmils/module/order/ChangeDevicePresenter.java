package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

/**
 * Copyright (c) 2017/12/2. LiaoPeiKun Inc. All rights reserved.
 */

public class ChangeDevicePresenter extends Presenter<ChangeDeviceActivity> {

    private Order mOrder;

    public static void start(Context context, Order order) {
        Intent intent = new Intent(context, ChangeDeviceActivity.class);
        intent.putExtra("order", order);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(ChangeDeviceActivity view, Bundle saveState) {
        mOrder = view.getIntent().getParcelableExtra("order");
    }

    @Override
    protected void onCreateView(ChangeDeviceActivity view) {
        if (mOrder != null) view.setToolbarTitle(String.format("%s家庭信息", mOrder.getCustName()));
    }

    public void changeHeat(String num) {
        ClientModel.getInstance().changeHeat(mOrder.getProjectId(), Integer.valueOf(num), getView().getDevice().getSpec())
                .unsafeSubscribe(new ServicesResponse<Project>() {
                    @Override
                    public void onNext(Project project) {
                        ParamDetailPresenter.start(getView(), mOrder.getProjectId(), 0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
