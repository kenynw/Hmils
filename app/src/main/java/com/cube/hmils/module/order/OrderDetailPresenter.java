package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;

import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

/**
 * Created by Carol on 2017/11/19.
 */

public class OrderDetailPresenter extends BaseDataActivityPresenter<OrderDetailActivity, RoomOrder> {

    public static void start(Context context, int projectId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("projectId", projectId);
        context.startActivity(intent);
    }

}
