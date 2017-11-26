package com.cube.hmils.module.user;

import android.net.Uri;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
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

    private Client mClient;

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        mClient = getView().getIntent().getParcelableExtra(EXTRA_CLIENT);
        if (mClient != null) {
            getView().setClientInfo(mClient);
            loadData();
        }
        User user = getView().getIntent().getParcelableExtra(EXTRA_USER);
        if (user != null) {
            getView().setProfile(user);
        }
    }

    private void loadData() {
        ClientModel.getInstance().getClientDetail(mClient.getCustId(), mClient.getProjectId())
                .subscribe(new ServicesResponse<Client>() {
                    @Override
                    public void onNext(Client client) {
                        getView().setClientInfo(client);
                    }
                });
    }

    public void saveProfile(Uri uri, String username, String phone) {
        UserModel.getInstance().saveProfile(uri == null ? null : new File(uri.getPath()), username, phone)
                .unsafeSubscribe(new ServicesResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        LUtils.toast("修改成功");
                    }
                });
    }

    public void saveClient(String name, String phone, String province, String city, String district, String address) {
        ClientModel.getInstance().saveClientInfo(mClient.getProjectId(), name, phone, province, city, district, address)
                .subscribe(new ServicesResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        LUtils.toast("修改成功");
                        mClient.setCustName(name);
                        mClient.setPhoneNo(phone);
                        mClient.setCity(city);
                        mClient.setDistrict(district);
                        mClient.setDetailAddr(address);
                        ClientDetailPresenter.start(getView(), mClient);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.EDIT_ADDRESS) {
            String province = bundle.getString("province");
            String city = bundle.getString("city");
            String district = bundle.getString("district");
            mClient.setProvince(province);
            mClient.setCity(city);
            mClient.setDistrict(district);
        }
    }

}
