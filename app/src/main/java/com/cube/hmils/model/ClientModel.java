package com.cube.hmils.model;

import com.cube.hmils.model.bean.ClientList;
import com.cube.hmils.model.local.UserPreferences;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.model.services.ServicesClient;
import com.cube.hmils.utils.StringUtil;
import com.dsk.chain.model.AbsModel;

import java.util.Collections;

import rx.Observable;

/**
 * Created by Carol on 2017/11/12.
 */

public class ClientModel extends AbsModel {

    public static ClientModel getInstance() {
        return getInstance(ClientModel.class);
    }

    public Observable<ClientList> getClientList(String keywords) {
        return ServicesClient.getServices().getCustList(UserPreferences.getUserID(), keywords)
                .map(clientList -> {
                    if (!clientList.getCustList().isEmpty()) {
                        Collections.sort(clientList.getCustList(), (clientFirst, clientSecond) ->
                                StringUtil.getFirstLetter(clientFirst.getCustName())
                                        .compareTo(StringUtil.getFirstLetter(clientSecond.getCustName()))
                        );
                    }
                    return clientList;
                })
                .compose(new DefaultTransform<>());
    }

}
