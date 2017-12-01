package com.cube.hmils.model;

import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.ClientList;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderList;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.RoomOrder;
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

    /**
     * 客户列表
     *
     * @param keywords
     * @return
     */
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

    /**
     * 客户详情
     *
     * @param clientId
     * @param projectId
     * @return
     */
    public Observable<Client> getClientDetail(int clientId, int projectId) {
        return ServicesClient.getServices().getClientDetail(UserPreferences.getUserID(), clientId, projectId)
                .compose(new DefaultTransform<>());
    }

    /**
     * 完善该用户信息
     *
     */
    public Observable<Response> saveClientInfo(int projectId, String clientName, String phone, String province,
                                               String city, String district, String detailAddr) {
        return ServicesClient.getServices().saveClientInfo(projectId, clientName, phone, province, city, district, detailAddr)
                .compose(new DefaultTransform<>());
    }

    public Observable<OrderList> getOrderList(String search, int state) {
        return ServicesClient.getServices().orderList(UserPreferences.getUserID(), search, state)
                .compose(new DefaultTransform<>());
    }

    /**
     * 我的售后列表
     * @return
     */
    public Observable<OrderList> getServiceList(int state) {
        return ServicesClient.getServices().servicesList(UserPreferences.getUserID(), state)
                .compose(new DefaultTransform<>());
    }

    /**
     * 订单详情（房间）
     * @param custId
     * @param projectId
     * @return
     */
    public Observable<RoomOrder> getOrderDetail(int custId, int projectId) {
        return ServicesClient.getServices().orderDetail(custId, projectId)
                .compose(new DefaultTransform<>());
    }

    public Observable<Order> createOrder(int custId, int projectId, int type) {
        return ServicesClient.getServices().createOrder(custId, projectId, type)
                .compose(new DefaultTransform<>());
    }

    public Observable<Project> addRoomNum(int projectId, int roomNum) {
        return ServicesClient.getServices().addRoomNum(projectId, roomNum)
                .compose(new DefaultTransform<>());
    }

    /**
     *
     * @param addArea
     * @param addStatus
     * @param itemId
     * @param projectId
     * @param roomName
     * @param roomSize
     * @param roomType 标准房间传1，不规则房间传0
     * @return
     */
    public Observable<Project> saveRoomParams(int addArea, String addStatus, int itemId, int projectId,
                                              String roomName, String roomSize, int roomType) {
        return ServicesClient.getServices().saveRoomParams(addArea, addStatus, itemId, projectId, roomName, roomSize, roomType)
                .compose(new DefaultTransform<>());
    }

    /**
     * 获取总订单
     *
     * @param projectId
     * @return
     */
    public Observable<RoomOrder> getTotalOrder(int projectId) {
        return ServicesClient.getServices().getTotalOrder(projectId)
                .compose(new DefaultTransform<>());
    }

    /**
     * 获取总订单
     *
     * @param projectId
     * @return
     */
    public Observable<RoomOrder> comfirmOrder(int projectId, int type) {
        return ServicesClient.getServices().comfirmOrder(projectId, type)
                .compose(new DefaultTransform<>());
    }

}
