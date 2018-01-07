package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carol on 2017/12/2.
 */

public class ParamDetailPresenter extends BaseDataActivityPresenter<ParamDetailActivity, RoomOrder> {

    private int mProjectId;
    private int mType; // 页面类型 0-未提交支付 1-生成订单后 2-修改订单(编辑模式)

    public static void start(Context context, int projectId, int type) {
        Intent intent = new Intent(context, ParamDetailActivity.class);
        intent.putExtra("projectId", projectId);
        intent.putExtra("type", type);
        if (type == 0) intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(ParamDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mProjectId = view.getIntent().getIntExtra("projectId", 0);
        mType = view.getIntent().getIntExtra("type", 0);
    }

    @Override
    protected void onCreateView(ParamDetailActivity view) {
        super.onCreateView(view);
        loadData();
    }

    private void loadData() {
        ClientModel.getInstance().getOrderDetail(0, mProjectId).unsafeSubscribe(getDataSubscriber());
    }

    public void updateOrder(String order) {
        ClientModel.getInstance().updateOrder(order).unsafeSubscribe(new ServicesResponse<Response>() {
            @Override
            public void onNext(Response response) {
                Bundle bundle = new Bundle();
                bundle.putInt(EVENT_BUS_CODE, EventCode.ORDER_DETAIL_UPDATE);
                EventBus.getDefault().post(bundle);
                bundle.putInt(EVENT_BUS_CODE, EventCode.PARAM_DETAIL_UPDATE);
                EventBus.getDefault().post(bundle);
                getView().finish();
            }

            @Override
            public void onError(Throwable e) {
                LUtils.log(Log.getStackTraceString(e));
            }
        });
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.PARAM_DETAIL_FINISH) {
            getView().finish();
        }
        if (eventCode == EventCode.PARAM_DETAIL_UPDATE) {
            loadData();
        }
    }

    public int getType() {
        return mType;
    }

    public int getProjectId() {
        return mProjectId;
    }

}
