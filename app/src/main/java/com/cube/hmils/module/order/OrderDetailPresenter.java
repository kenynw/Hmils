package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.model.bean.RoomOrderRes;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.EventBusUtil;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

/**
 * Created by Carol on 2017/11/19.
 */

public class OrderDetailPresenter extends BaseDataActivityPresenter<OrderDetailActivity, RoomOrder> {

    private int mProjectId;

    public static void start(Context context, int projectId, int type) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("projectId", projectId);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(OrderDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mProjectId = view.getIntent().getIntExtra("projectId", 0);
    }

    @Override
    protected void onCreateView(OrderDetailActivity view) {
        super.onCreateView(view);
        loadData();
    }

    private void loadData() {
        ClientModel.getInstance().getTotalOrder(mProjectId).unsafeSubscribe(getDataSubscriber());
    }

    public void confirm() {
        ClientModel.getInstance().comfirmOrder(mProjectId).unsafeSubscribe(new ServicesResponse<RoomOrderRes>() {
            @Override
            public void onNext(RoomOrderRes roomOrderRes) {
                LUtils.log("提交成功");
                getView().finish();
                EventBusUtil.eventPost(EventCode.ROOM_PARAMS_FINISH);
                EventBusUtil.eventPost(EventCode.PARAM_DETAIL_FINISH);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.ORDER_DETAIL_UPDATE) {
            loadData();
        }
    }

    public int getProjectId() {
        return mProjectId;
    }

}
