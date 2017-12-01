package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;

/**
 * Created by Carol on 2017/11/22.
 */

public class RoomOrderFragmentPresenter extends BaseDataFragmentPresenter<RoomOrderFragment, RoomOrder> {

    private int mProjectId;

    private RoomOrder mRoomOrder;

    public static RoomOrderFragment newInstance(int projectId) {
        RoomOrderFragment fragment = new RoomOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("projectId", projectId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static RoomOrderFragment newInstance(RoomOrder roomOrder) {
        RoomOrderFragment fragment = new RoomOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("roomOrder", roomOrder);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreate(RoomOrderFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (view.getArguments() != null) {
            mProjectId = view.getArguments().getInt("projectId", 0);
            mRoomOrder = view.getArguments().getParcelable("roomOrder");
        }
    }

    @Override
    protected void onCreateView(RoomOrderFragment view) {
        super.onCreateView(view);
        if (mRoomOrder != null) {
            view.setData(mRoomOrder);
        } else {
            loadData();
        }
    }

    private void loadData() {


//        ClientModel.getInstance().getOrderDetail(5, 115)
//                .doOnNext(roomOrder -> mRoomOrder = roomOrder)
//                .unsafeSubscribe(getSubscriber());
    }

    public RoomOrder getRoomOrder() {
        return mRoomOrder;
    }

}