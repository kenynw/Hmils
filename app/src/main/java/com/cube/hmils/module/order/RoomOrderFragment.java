package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    @BindView(R.id.iv_room_order_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_room_order_position)
    TextView mTvPosition;
    @BindView(R.id.iv_room_order_right)
    ImageView mIvRight;
    @BindView(R.id.rv_room_order_material)
    RecyclerView mRvMaterial;
    @BindView(R.id.tv_room_order_count_material)
    TextView mTvCountMaterial;
    @BindView(R.id.rv_room_order_heating)
    RecyclerView mRvHeating;
    @BindView(R.id.tv_room_order_count_heating)
    TextView mTvCountHeating;

    Unbinder unbinder;

    private int mPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment_room_order, null);
        unbinder = ButterKnife.bind(this, view);

        mRvMaterial.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvHeating.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIvLeft.setOnClickListener(v -> {
            if (mPosition > 0) mPosition -= mPosition;
            setData(getPresenter().getRoomOrder());
        });
        mIvRight.setOnClickListener(v -> {
            if (mPosition < getPresenter().getRoomOrder().getRoomOrder().size())
                mPosition += mPosition;
            setData(getPresenter().getRoomOrder());
        });

        return view;
    }

    @Override
    public void setData(RoomOrder roomOrders) {
        mTvPosition.setText(String.format("%1$d of %2$d", mPosition, roomOrders.getRoomOrder().size()));
        RoomOrder roomOrder = roomOrders.getRoomOrder().get(mPosition);
        mTvCountMaterial.setText(String.format(getString(R.string.text_count_product), roomOrder.getMGoods()));
        mTvCountHeating.setText(String.format(getString(R.string.text_count_product), roomOrder.getHGoods()));
        mRvHeating.setAdapter( new DeviceRecyclerAdapter(getActivity(), roomOrder.getHeatingList()));
        mRvMaterial.setAdapter(new DeviceRecyclerAdapter(getActivity(), roomOrder.getMaterialList()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
