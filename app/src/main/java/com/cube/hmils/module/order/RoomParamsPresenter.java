package com.cube.hmils.module.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Params;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.bean.Room;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.constant.Extra;
import com.cube.hmils.model.local.DaoSharedPreferences;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Carol on 2017/10/15.
 */

public class RoomParamsPresenter extends Presenter<RoomParamsActivity> {

    private int mProjectId;
    private int mMelType;
    private int[] mRoomIds;

    private int mPosition;

    public static void start(Context context, int projectId, int[] roomNum, int melType, int position) {
        Intent intent = new Intent(context, RoomParamsActivity.class);
        intent.putExtra(Extra.EXTRA_PROJECT_ID, projectId);
        intent.putExtra(Extra.EXTRA_MATERIAL_TYPE, melType);
        intent.putExtra(Extra.EXTRA_ROOM_NUM, roomNum);
        intent.putExtra(Extra.EXTRA_POSITION, position);
        context.startActivity(intent);
    }

    public static void start(Context context, Params params) {
        Intent intent = new Intent(context, RoomParamsActivity.class);
        intent.putExtra(Extra.EXTRA_PARAM_ENTITY, params);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(RoomParamsActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mProjectId = view.getIntent().getIntExtra(Extra.EXTRA_PROJECT_ID, 0);
        mRoomIds = view.getIntent().getIntArrayExtra(Extra.EXTRA_ROOM_NUM);
        mMelType = view.getIntent().getIntExtra(Extra.EXTRA_MATERIAL_TYPE, 0);
        mPosition = view.getIntent().getIntExtra(Extra.EXTRA_POSITION, 0);
    }

    @Override
    protected void onCreateView(RoomParamsActivity view) {
        super.onCreateView(view);
        view.setToolbarTitle(String.format("%1$d of %2$d", mPosition + 1, mRoomIds.length));
        getData();
    }

    private void getData() {
        Params roomParams = DaoSharedPreferences.getInstance().getRoomParams(getSuffix());
        if (roomParams != null) {
            getView().setData(roomParams);
        }
    }

    public void saveParams(List<Room> addAreas, List<Room> minuAreas, String roomName, List<Room> roomSizes, int roomType) {
        String addArea = new Gson().toJson(addAreas);
        String minuArea = new Gson().toJson(minuAreas);
        String roomSize = new Gson().toJson(roomSizes);
        String isEnd = mPosition == mRoomIds.length - 1 ? "end" : "";

        ClientModel.getInstance().saveRoomParams(addArea, minuArea, isEnd, mRoomIds[mPosition], mProjectId,
                roomName, roomSize, roomType, mMelType)
                .subscribe(new ServicesResponse<Project>() {
                    @Override
                    public void onNext(Project project) {
                        LUtils.log("itemId: " + mRoomIds[mPosition] + ", projectId: " + mProjectId);
                        if (isEnd.equals("end")) {
                            ParamDetailPresenter.start(getView(), mProjectId, 0);
                        } else {
                            RoomParamsPresenter.start(getView(), mProjectId, mRoomIds, mMelType, mPosition + 1);
                        }

                        Params params = new Params();
                        params.setProjectId(mProjectId);
                        params.setItemIds(mRoomIds);
                        params.setMelType(mMelType);
                        params.setPosition(mPosition);
                        params.setAddAreas(addAreas);
                        params.setMinuAreas(minuAreas);
                        params.setRooms(roomSizes);
                        DaoSharedPreferences.getInstance().setRoomParams(params, getSuffix());
                    }
                });
    }

    private String getSuffix() {
        return String.valueOf(mProjectId) + mPosition;
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.ROOM_PARAMS_FINISH) {
            getView().finish();
        }
    }

}
