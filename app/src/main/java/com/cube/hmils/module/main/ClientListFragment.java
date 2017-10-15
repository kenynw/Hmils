package com.cube.hmils.module.main;

import android.view.ViewGroup;

import com.cube.hmils.model.bean.Client;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Carol on 2017/10/11.
 */
@RequiresPresenter(ClientListPresenter.class)
public class ClientListFragment extends BaseListFragment<ClientListPresenter, Client> {
    @Override
    public BaseViewHolder<Client> createViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
