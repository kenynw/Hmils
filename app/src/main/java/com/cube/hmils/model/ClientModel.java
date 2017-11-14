package com.cube.hmils.model;

import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.ClientList;
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
        return ServicesClient.getServices().getCustList(20, keywords)
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

    public Observable<Client> getClientDetail(int clientId, int projectId) {
        return ServicesClient.getServices().getClientDetail(20, clientId, projectId)
                .compose(new DefaultTransform<>());
    }

}
