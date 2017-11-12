package com.cube.hmils.module.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.ClientList;
import com.cube.hmils.utils.StringUtil;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;

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
        mClients = new ArrayList<>();
        for (int i=0; i<12; i++) {
            Client client = new Client();
            client.setContTel("12423143");
            client.setCustId(25107 + i);
            client.setCustName("周杰伦");
            mClients.add(client);
        }
        for (int i=0; i<13; i++) {
            Client client = new Client();
            client.setContTel("12423143");
            client.setCustId(25107 + i);
            client.setCustName("廖培坤");
            mClients.add(client);
        }

        Client client = new Client();
        client.setContTel("12423143");
        client.setCustId(2510);
        client.setCustName("艾丽娅");
        mClients.add(client);

        Collections.sort(mClients, (clientFirst, clientSecond) ->
                StringUtil.getFirstLetter(clientFirst.getCustName())
                        .compareTo(StringUtil.getFirstLetter(clientSecond.getCustName()))
        );

        ClientList list = new ClientList();
        list.setCustList(mClients);
        list.setNewCust(mClients);
        Observable.just(list.getCustList())
                .doOnNext(clients -> getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
                    @Override
                    public View onCreateView(ViewGroup parent) {
                        return new RecyclerView(getView().getActivity());
                    }

                    @Override
                    public void onBindView(View headerView) {
                        RecyclerView recyclerView = (RecyclerView) headerView;
                        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getActivity()));
                        recyclerView.setAdapter(new RecyclerArrayAdapter<Client>(getView().getActivity(), list.getNewCust()) {
                            @Override
                            public ClientHeaderViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                                return new ClientHeaderViewHolder(parent);
                            }

                            @Override
                            public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
                                holder.setData(list.getNewCust().get(position));
                            }

                        });
                    }
                }))
                .subscribe(getRefreshSubscriber());
    }

    public List<Client> getClients() {
        return mClients;
    }

}
