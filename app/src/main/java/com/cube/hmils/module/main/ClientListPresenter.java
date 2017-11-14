package com.cube.hmils.module.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Client;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Carol on 2017/10/11.
 */

public class ClientListPresenter extends BaseListFragmentPresenter<ClientListFragment, Client> {

    private List<Client> mClients;

    @Override
    protected void onCreateView(ClientListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ClientModel.getInstance().getClientList("")
                .map(clientList -> {
                    getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
                        @Override
                        public View onCreateView(ViewGroup parent) {
                            return new RecyclerView(getView().getActivity());
                        }

                        @Override
                        public void onBindView(View headerView) {
                            RecyclerView recyclerView = (RecyclerView) headerView;
                            recyclerView.setLayoutManager(new LinearLayoutManager(getView().getActivity()));
                            recyclerView.setAdapter(new RecyclerArrayAdapter<Client>(getView().getActivity(), clientList.getNewCust()) {
                                @Override
                                public ClientHeaderViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                                    return new ClientHeaderViewHolder(parent);
                                }

                                @Override
                                public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
                                    holder.setData(clientList.getNewCust().get(position));
                                }

                            });
                        }
                    });
                    mClients = clientList.getCustList();
                    return clientList.getCustList();
                })
                .subscribe(getRefreshSubscriber());
    }

    public List<Client> getClients() {
        return mClients;

    }

}
