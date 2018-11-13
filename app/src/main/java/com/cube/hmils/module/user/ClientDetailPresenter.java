package com.cube.hmils.module.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cube.hmils.app.Navigator;
import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

/**
 * Created by Carol on 2017/10/30.
 */

public class ClientDetailPresenter extends BaseDataActivityPresenter<ClientDetailActivity, Client> {

    public static final String EXTRA_CLIENT = "client";

    public static void start(Context context, Client client) {
        Intent intent = new Intent(context, ClientDetailActivity.class);
        intent.putExtra(EXTRA_CLIENT, client);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private Client mClient;

    @Override
    protected void onCreate(ClientDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mClient = getView().getIntent().getParcelableExtra(EXTRA_CLIENT);
    }

    @Override
    protected void onCreateView(ClientDetailActivity view) {
        super.onCreateView(view);
        loadData();
    }

    private void loadData() {
        ClientModel.getInstance().getClientDetail(mClient.getCustId(), mClient.getProjectId())
                .doOnNext(client -> mClient = client)
                .unsafeSubscribe(getDataSubscriber());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mClient = intent.getParcelableExtra(EXTRA_CLIENT);
        getView().setData(mClient);
    }

    public Client getClient() {
        return mClient;
    }

    public void createOrder() {
        ClientModel.getInstance().createOrder(mClient.getCustId(), mClient.getProjectId(), mClient.getCustType() == 0 ? "01" : "02")
                .subscribe(new ServicesResponse<Order>() {
                    @Override
                    public void onNext(Order order) {
                        Navigator.getInstance().openCreateOrderActivity(order.getProjectId() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LUtils.log(Log.getStackTraceString(e));
                    }
                });
    }

}
