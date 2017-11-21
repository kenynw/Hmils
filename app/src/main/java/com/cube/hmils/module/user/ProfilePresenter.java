package com.cube.hmils.module.user;

import android.net.Uri;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;

import java.io.File;

/**
 * Created by Carol on 2017/10/29.
 */

public class ProfilePresenter extends Presenter<ProfileActivity> {

    public static final String EXTRA_CLIENT = "client"; // 完善客户资料

    public static final String EXTRA_USER = "user"; // 完善客户资料

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        Client client = getView().getIntent().getParcelableExtra(EXTRA_CLIENT);
        if (client != null) {
            getView().setClientInfo(client);
            loadData(client.getCustId(), client.getProjectId());
        }
        User user = getView().getIntent().getParcelableExtra(EXTRA_USER);
        if (user != null) {
            getView().setProfile(user);
        }
    }

    private void loadData(int clientId, int projectId) {
        ClientModel.getInstance().getClientDetail(clientId, projectId).subscribe(new ServicesResponse<Client>() {
            @Override
            public void onNext(Client client) {
                getView().setClientInfo(client);
            }
        });
    }

    public void save(Uri uri, String username, String phone) {
        UserModel.getInstance().saveProfile(uri == null ? null : new File(uri.getPath()), username, phone)
                .unsafeSubscribe(new ServicesResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        LUtils.toast("修改成功");
                    }
                });
    }

}
