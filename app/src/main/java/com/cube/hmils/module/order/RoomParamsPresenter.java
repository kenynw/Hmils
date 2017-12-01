package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/15.
 */

public class RoomParamsPresenter extends Presenter<RoomParamsActivity> {

    public static final String EXTRA_ROOM_NUM = "room_num";

    public static final String EXTRA_ORDER = "order";

    private Order mOrder;

    private int[] mRoomIds;

    private int mPosition;

    public static void start(Context context, Order order, int[] roomNum, int position) {
        Intent intent = new Intent(context, RoomParamsActivity.class);
        intent.putExtra(EXTRA_ORDER, order);
        intent.putExtra(EXTRA_ROOM_NUM, roomNum);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(RoomParamsActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mOrder = view.getIntent().getParcelableExtra(EXTRA_ORDER);
        mRoomIds = view.getIntent().getIntArrayExtra(EXTRA_ROOM_NUM);
        mPosition = view.getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void onCreateView(RoomParamsActivity view) {
        super.onCreateView(view);
        view.setToolbarTitle(String.format("%1$d of %2$d", mPosition + 1, mRoomIds.length));
    }

    public void saveParams(int addArea, String roomName, String roomSize, int roomType) {
        String isEnd = mPosition == mRoomIds.length - 1 ? "end" : "";

        ClientModel.getInstance().saveRoomParams(addArea, isEnd, mRoomIds[0], mOrder.getProjectId(),
                roomName, roomSize, roomType)
                .subscribe(new ServicesResponse<Project>() {
                    @Override
                    public void onNext(Project project) {
                        if (isEnd.equals("end")) {
                            OrderDetailPresenter.start(getView(), mOrder.getProjectId(), 1);
                        } else {
                            RoomParamsPresenter.start(getView(), mOrder, mRoomIds, mPosition + 1);
                        }
                    }
                });
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.ROOM_PARAMS_FINISH) {
            getView().finish();
        }
    }
}
