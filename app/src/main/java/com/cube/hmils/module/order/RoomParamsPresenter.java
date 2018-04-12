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
import com.cube.hmils.utils.EventBusUtil;
import com.cube.hmils.utils.GsonUtil;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Carol on 2017/10/15.
 */

public class RoomParamsPresenter extends Presenter<RoomParamsActivity> {

    private int mProjectId;
    private int mMelType, mFloorType;
    private int[] mRoomIds;

    private int mPosition;
    private Params mParams;

    public static void start(Context context, int projectId, int[] roomNum, int position) {
        Intent intent = new Intent(context, RoomParamsActivity.class);
        intent.putExtra(Extra.EXTRA_PROJECT_ID, projectId);
        intent.putExtra(Extra.EXTRA_ROOM_NUM, roomNum);
        intent.putExtra(Extra.EXTRA_POSITION, position);
        context.startActivity(intent);
    }

    public static void start(Context context, Params params) {
        Intent intent = new Intent(context, RoomParamsActivity.class);
        intent.putExtra(Extra.EXTRA_PARAM_ENTITY, params);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(RoomParamsActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mParams = view.getIntent().getParcelableExtra(Extra.EXTRA_PARAM_ENTITY);
        if (mParams != null) {
            mProjectId = mParams.getProjectId();
            mRoomIds = mParams.getItemIds();
            mMelType = mParams.getMelType();
            mPosition = mParams.getPosition();
        } else {
            mProjectId = view.getIntent().getIntExtra(Extra.EXTRA_PROJECT_ID, 0);
            mRoomIds = view.getIntent().getIntArrayExtra(Extra.EXTRA_ROOM_NUM);
            mMelType = view.getIntent().getIntExtra(Extra.EXTRA_MATERIAL_TYPE, 0);
            mPosition = view.getIntent().getIntExtra(Extra.EXTRA_POSITION, 0);
        }
    }

    @Override
    protected void onCreateView(RoomParamsActivity view) {
        super.onCreateView(view);
        view.setToolbarTitle(String.format("%1$d of %2$d", mPosition + 1, mRoomIds.length));
        if (mParams != null) {
            getView().setData(mParams);
            LUtils.log(GsonUtil.toJson(mParams));
        }
    }

    public void saveParams(List<Room> addAreas, List<Room> minuAreas, String roomName, List<Room> roomSizes, int roomType) {
        String addArea = new Gson().toJson(addAreas);
        String minuArea = new Gson().toJson(minuAreas);
        String roomSize = new Gson().toJson(roomSizes);
        String isEnd = mPosition == mRoomIds.length - 1 ? "end" : "";



        ClientModel.getInstance().saveRoomParams(addArea, minuArea, isEnd, mRoomIds[mPosition], mProjectId,
                roomName, roomSize, roomType, getView().getMelType(), getView().getFloorType())
                .subscribe(new ServicesResponse<Project>() {
                    @Override
                    public void onNext(Project project) {
                        LUtils.log("itemId: " + mRoomIds[mPosition] + ", projectId: " + mProjectId);
                        if (isEnd.equals("end")) {
                            ParamDetailPresenter.start(getView(), mProjectId, 0);
                            EventBusUtil.eventPost(EventCode.PARAM_DETAIL_UPDATE);
                        } else {
                            String suffix = String.valueOf(mProjectId) + (mPosition + 1);
                            Params params = DaoSharedPreferences.getInstance().getRoomParams(suffix);
                            if (params != null) {
                                Intent intent = new Intent(getView(), RoomParamsActivity.class);
                                intent.putExtra(Extra.EXTRA_PARAM_ENTITY, params);
                                getView().startActivity(intent);
                            } else {
                                RoomParamsPresenter.start(getView(), mProjectId, mRoomIds, mPosition + 1);
                            }
                        }

                        Params params = new Params();
                        params.setProjectId(mProjectId);
                        params.setItemIds(mRoomIds);
                        params.setMelType(mMelType);
                        params.setPosition(mPosition);
                        params.setName(roomName);
                        params.setAddAreas(addAreas);
                        params.setMinuAreas(minuAreas);
                        params.setRooms(roomSizes);
                        params.setIsSteady(roomType);
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
