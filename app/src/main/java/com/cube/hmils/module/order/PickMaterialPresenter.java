package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;

import com.cube.hmils.model.bean.Order;
import com.dsk.chain.bijection.Presenter;

import static com.cube.hmils.model.constant.ExtraConstant.EXTRA_ORDER;
import static com.cube.hmils.model.constant.ExtraConstant.EXTRA_ROOM_NUM;

/**
 * Copyright (c) 2017/12/10. LiaoPeiKun Inc. All rights reserved.
 */

public class PickMaterialPresenter extends Presenter<PickMaterialActivity> {

    public static void start(Context context, Order order, int[] roomNum) {
        Intent intent = new Intent(context, PickMaterialActivity.class);
        intent.putExtra(EXTRA_ORDER, order);
        intent.putExtra(EXTRA_ROOM_NUM, roomNum);
        context.startActivity(intent);
    }

}
