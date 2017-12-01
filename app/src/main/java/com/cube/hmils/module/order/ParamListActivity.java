package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;

@RequiresPresenter(ParamListPresenter.class)
public class ParamListActivity extends BaseDataActivity<ParamListPresenter, RoomOrder> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_param_list);
    }

    @Override
    public void setData(RoomOrder roomOrder) {
        setToolbarTitle(roomOrder.getRoomName());
        RoomOrderFragment fragment = RoomOrderFragmentPresenter.newInstance(roomOrder);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_param_list_container, fragment)
                .commit();
    }
}
