package com.cube.hmils.module.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.cube.hmils.R;
import com.cube.hmils.model.Service;
import com.cube.hmils.module.main.ServiceViewHolder;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Carol on 2017/10/14.
 */
@RequiresPresenter(ServiceListPresenter.class)
public class ServiceListFragment extends BaseListFragment<ServiceListPresenter, Service> {

    @Override
    public BaseViewHolder<Service> createViewHolder(ViewGroup parent, int viewType) {
        return new ServiceViewHolder(parent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getListView().setClipToPadding(false);
        getListView().setRecyclerPadding(0, LUtils.dp2px(10), 0, 0);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig()
                .setLoadMoreAble(false)
                .setNoMoreAble(false)
                .setFooterErrorAble(false)
                .setContainerEmptyRes(R.layout.def_empty_layout);
    }

}
