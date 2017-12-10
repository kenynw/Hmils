package com.cube.hmils.module.user;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.City;
import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.Dist;
import com.cube.hmils.model.bean.Province;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.model.services.ServicesResponse;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * Created by Carol on 2017/10/29.
 */

public class ProfilePresenter extends Presenter<ProfileActivity> {

    public static final String EXTRA_CLIENT = "client"; // 完善客户资料

    public static final String EXTRA_USER = "user"; // 完善客户资料

    private Client mClient;

    private Province mProvince;

    private City mCity;

    private Dist mDistrict;


    // 完善客户资料
    public static final void start(Context context, Client client) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(EXTRA_CLIENT, client);
        context.startActivity(intent);
    }

    // 修改个人资料
    public static final void start(Context context, User user) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(EXTRA_USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mClient = getView().getIntent().getParcelableExtra(EXTRA_CLIENT);
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        if (mClient != null) {
            getView().setClientInfo(mClient);
//            loadData();
        }
        User user = getView().getIntent().getParcelableExtra(EXTRA_USER);
        if (user != null) {
            getView().setProfile(user);
        }
    }

    public void saveProfile(Uri uri, String username, String phone) {
        UserModel.getInstance().saveProfile(uri == null ? null : new File(uri.getPath()), username, phone)
                .unsafeSubscribe(new ServicesResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(EVENT_BUS_CODE, EventCode.CODE_ME_UPDATE);
                        EventBus.getDefault().post(bundle);
                        LUtils.toast("修改成功");
                    }
                });
    }

    public void saveClient(String name, String phone) {
        ClientModel.getInstance().saveClientInfo(mClient.getProjectId(), name, phone,mProvince.getProvinceCode(),
                mCity.getCityCode(), mDistrict.getDistCode(), mClient.getDetailAddr())
                .subscribe(new ServicesResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        LUtils.toast("修改成功");
                        mClient.setCustName(name);
                        mClient.setPhoneNo(phone);
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
            mProvince = bundle.getParcelable("province");
            mCity = bundle.getParcelable("city");
            mDistrict = bundle.getParcelable("district");
            String address = bundle.getString("address");
            mClient.setProvince(mProvince.getProvinceName());
            mClient.setCity(mCity.getCityName());
            mClient.setDistrict(mDistrict.getDistName());
            mClient.setDetailAddr(address);
            getView().setAddress(mClient.getFullAddress());
        }
    }

}
