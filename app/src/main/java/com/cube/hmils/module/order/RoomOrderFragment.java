package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cube.hmils.R;
import com.cube.hmils.model.bean.RoomOrder;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Carol on 2017/11/22.
 */
@RequiresPresenter(RoomOrderFragmentPresenter.class)
public class RoomOrderFragment extends BaseDataFragment<RoomOrderFragmentPresenter, RoomOrder> {

    @BindView(R.id.rv_room_order_material)
    RecyclerView mRvMaterial;
    @BindView(R.id.tv_room_order_count_material)
    TextView mTvCountMaterial;
    @BindView(R.id.rv_room_order_heating)
    RecyclerView mRvHeating;
    @BindView(R.id.tv_room_order_count_heating)
    TextView mTvCountHeating;

    Unbinder unbinder;
    private DeviceRecyclerAdapter mHeatingAdapter;
    private DeviceRecyclerAdapter mMaterialAdatper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment_room_order, null);
        unbinder = ButterKnife.bind(this, view);

        boolean isEdit = getArguments().getBoolean("isEdit", false);

        mRvMaterial.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMaterialAdatper = new DeviceRecyclerAdapter(getActivity(), isEdit);
        mRvMaterial.setAdapter(mMaterialAdatper);

        mRvHeating.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHeatingAdapter = new DeviceRecyclerAdapter(getActivity(), isEdit);
        mRvHeating.setAdapter(mHeatingAdapter);

        return view;
    }

    @Override
    public void setData(RoomOrder roomOrders) {
        RoomOrder roomOrder = roomOrders.getRoomOrder() == null ? roomOrders : roomOrders.getRoomOrder();
        mTvCountMaterial.setText(String.format(getString(R.string.text_count_product), roomOrder.getMGoods()));
        mTvCountHeating.setText(String.format(getString(R.string.text_count_product), roomOrder.getHGoods()));
        mHeatingAdapter.addAll(roomOrder.getHeatingList());
        mMaterialAdatper.addAll(roomOrder.getMaterialList());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public RoomOrder getModifyList() {
        RoomOrder roomOrder = getPresenter().getRoomOrder();
        if (mMaterialAdatper.getModifyItem() != null && mMaterialAdatper.getModifyItem().size() > 0)
            roomOrder.setMaterialList(mMaterialAdatper.getModifyItem());
        if (mHeatingAdapter.getModifyItem() != null && mMaterialAdatper.getModifyItem().size() > 0) {
            roomOrder.setHeatingList(mHeatingAdapter.getModifyItem());
        }
        return roomOrder;
    }

}
