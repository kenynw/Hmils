package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

/**
 * Created by Carol on 2017/12/2.
 */

public class ParamListPresenter extends BaseDataActivityPresenter<ParamListActivity, RoomOrder> {

    private RoomOrder mRoomOrder;

    public static void start(Context context, RoomOrder roomOrder) {
        Intent intent = new Intent(context, ParamListActivity.class);
        intent.putExtra("room_order", roomOrder);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(ParamListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mRoomOrder = view.getIntent().getParcelableExtra("room_order");
    }

    @Override
    protected void onCreateView(ParamListActivity view) {
        super.onCreateView(view);
        view.setData(mRoomOrder);
    }

}
