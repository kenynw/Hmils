package com.cube.hmils.module.main;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Carol on 2017/10/11.
 */
@RequiresPresenter(ServiceListPresenter.class)
public class ServiceListFragment extends BaseListFragment {

    @Override
    public BaseViewHolder createViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

}
