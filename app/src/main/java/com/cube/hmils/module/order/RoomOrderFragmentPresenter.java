package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;

/**
 * Created by Carol on 2017/11/22.
 */

public class RoomOrderFragmentPresenter extends BaseDataFragmentPresenter<RoomOrderFragment, RoomOrder> {

    private RoomOrder mRoomOrder;

    public static RoomOrderFragment newInstance(RoomOrder roomOrder, boolean isEdit) {
        RoomOrderFragment fragment = new RoomOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("roomOrder", roomOrder);
        bundle.putBoolean("isEdit", isEdit);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreate(RoomOrderFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (view.getArguments() != null) {
            mRoomOrder = view.getArguments().getParcelable("roomOrder");
        }
    }

    @Override
    protected void onCreateView(RoomOrderFragment view) {
        super.onCreateView(view);
        if (mRoomOrder != null) {
            view.setData(mRoomOrder);
        }
    }

    public RoomOrder getRoomOrder() {
        return mRoomOrder;
    }
}
