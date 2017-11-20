package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;

import com.cube.hmils.model.bean.Order;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.dsk.chain.expansion.list.BaseListActivityPresenter;

/**
 * Created by Carol on 2017/11/19.
 */

public class OrderDetailPresenter extends BaseListActivityPresenter<OrderDetailActivity, Order> {

    public static void start(Context context, int orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }

}
