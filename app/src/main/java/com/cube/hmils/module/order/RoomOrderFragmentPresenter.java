package com.cube.hmils.module.order;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;

/**
 * Created by Carol on 2017/11/22.
 */

public class RoomOrderFragmentPresenter extends BaseDataFragmentPresenter<RoomOrderFragment, RoomOrder> {

    private RoomOrder mRoomOrder;

    @Override
    protected void onCreateView(RoomOrderFragment view) {
        super.onCreateView(view);
        loadData();
    }

    private void loadData() {
        ClientModel.getInstance().getOrderDetail(5, 115)
                .doOnNext(roomOrder -> mRoomOrder = roomOrder)
                .unsafeSubscribe(getSubscriber());
    }

    public RoomOrder getRoomOrder() {
        return mRoomOrder;
    }
}
