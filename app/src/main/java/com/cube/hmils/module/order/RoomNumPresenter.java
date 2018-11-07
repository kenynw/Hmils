package com.cube.hmils.module.order;

import com.cube.hmils.model.ClientModel;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.services.ServicesResponse;
import com.dsk.chain.bijection.Presenter;

/**
 * Created by Carol on 2017/10/15.
 */

public class RoomNumPresenter extends Presenter<RoomNumActivity> {

    @Override
    protected void onCreateView(RoomNumActivity view) {
        if (getView().mProjectId != null)
            view.setToolbarTitle(String.format("%s家庭信息", getView().mClientName));
    }

    public void addNum(String num) {
        ClientModel.getInstance().addRoomNum(getView().mProjectId + "", Integer.valueOf(num))
                .subscribe(new ServicesResponse<Project>() {
                    @Override
                    public void onNext(Project project) {
                        RoomParamsPresenter.start(getView(), Integer.valueOf(getView().mProjectId), project.getItemId(), 0);
                        getView().finish();
                    }
                });
    }

}
