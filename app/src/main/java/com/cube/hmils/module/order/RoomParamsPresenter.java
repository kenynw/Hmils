package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/15.
 */

public class RoomParamsPresenter extends Presenter<RoomParamsActivity> {

    public static final String EXTRA_ROOM_NUM = "room_num";

    private int mRoomNum;

    public static void start(Context context, int roomNum) {
        Intent intent = new Intent(context, RoomParamsActivity.class);
        intent.putExtra(EXTRA_ROOM_NUM, roomNum);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(RoomParamsActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mRoomNum = getView().getIntent().getIntExtra(EXTRA_ROOM_NUM, 0);
    }

}
