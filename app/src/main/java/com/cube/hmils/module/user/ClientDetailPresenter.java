package com.cube.hmils.module.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cube.hmils.model.bean.Client;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/30.
 */

public class ClientDetailPresenter extends Presenter<ClientDetailActivity> {

    public static final String EXTRA_CLIENT = "client";

    public static void start(Context context, Client client) {
        Intent intent = new Intent(context, ClientDetailActivity.class);
        intent.putExtra(EXTRA_CLIENT, client);
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
        getView().setData(mClient);
    }

}
