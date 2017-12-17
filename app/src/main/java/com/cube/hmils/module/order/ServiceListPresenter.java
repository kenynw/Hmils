package com.cube.hmils.module.order;

import android.os.Bundle;

import com.cube.hmils.model.Service;
import com.cube.hmils.model.constant.EventCode;
import com.dsk.chain.expansion.list.BaseListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ServiceListPresenter extends BaseListFragmentPresenter<ServiceListFragment, Service> {

    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_SERVICE_LIST = "service_list";

    public static ServiceListFragment newInstance(int type, ArrayList<Service> list) {
        ServiceListFragment fragment = new ServiceListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_STATE, type);
        bundle.putParcelableArrayList(EXTRA_SERVICE_LIST, list);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int mState;

    private ArrayList<Service> mList;

    @Override
    protected void onCreate(ServiceListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);

        mState = view.getArguments().getInt(EXTRA_STATE, 0);
        mList = view.getArguments().getParcelableArrayList(EXTRA_SERVICE_LIST);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        Observable.just(mList).map(services -> {
            List<Service> list = new ArrayList<>();
            for (Service service : mList) {
                if (service.getProcCode() == mState) {
                    list.add(service);
                }
            }
            return mState == 0 ? mList : list;
        }).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.ORDER_LIST_UPDATE) {
            onRefresh();
        }
    }

}
