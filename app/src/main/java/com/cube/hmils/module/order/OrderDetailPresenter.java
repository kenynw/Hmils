package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carol on 2017/11/19.
 */

public class OrderDetailPresenter extends BaseDataActivityPresenter<OrderDetailActivity, RoomOrder> {

    private int mProjectId;

    private int mState;

    public static void start(Context context, int projectId, int state) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("projectId", projectId);
        intent.putExtra("state", state);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(OrderDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mProjectId = view.getIntent().getIntExtra("projectId", 0);
        mState = view.getIntent().getIntExtra("state", 0);
    }

    @Override
    protected void onCreateView(OrderDetailActivity view) {
        super.onCreateView(view);
        loadData();
    }

    private void loadData() {
        ClientModel.getInstance().getTotalOrder(mProjectId).unsafeSubscribe(getDataSubscriber());
    }

    public void confirm(int type) {
        ClientModel.getInstance().comfirmOrder(mProjectId, type).unsafeSubscribe(new ServicesResponse<RoomOrder>() {
            @Override
            public void onNext(RoomOrder roomOrder) {
                LUtils.log("成功");
                getView().finish();
                Bundle bundle = new Bundle();
                bundle.putInt(EVENT_BUS_CODE, EventCode.ORDER_LIST_UPDATE);
                EventBus.getDefault().post(bundle);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
