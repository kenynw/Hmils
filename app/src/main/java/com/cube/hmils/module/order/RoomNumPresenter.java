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
 * Created by Carol on 2017/10/15.
 */

public class RoomNumPresenter extends Presenter<RoomNumActivity> {

    private Order mOrder;

    public static void start(Context context, Order order) {
        Intent intent = new Intent(context, RoomNumActivity.class);
        intent.putExtra("order", order);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(RoomNumActivity view, Bundle saveState) {
        mOrder = view.getIntent().getParcelableExtra("order");
    }

    @Override
    protected void onCreateView(RoomNumActivity view) {
        if (mOrder != null) view.setToolbarTitle(String.format("%s家庭信息", mOrder.getCustName()));
    }

    public void addNum(String num) {
        ClientModel.getInstance().addRoomNum(mOrder.getProjectId(), Integer.valueOf(num))
                .subscribe(new ServicesResponse<Project>() {
                    @Override
                    public void onNext(Project project) {
                        RoomParamsPresenter.start(getView(), mOrder, project.getItemId(), 0);
                        getView().finish();
                    }
                });
    }

}
