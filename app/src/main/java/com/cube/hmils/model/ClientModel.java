package com.cube.hmils.model;

import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.ClientList;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderResponse;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.model.bean.User;
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
        User user = UserModel.getInstance().getUser();
        return ServicesClient.getServices().getCustList(user == null ? 0 : user.getUserId(), keywords)
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
        User user = UserModel.getInstance().getUser();
        return ServicesClient.getServices().getClientDetail(user == null ? 0 :user.getUserId(), clientId, projectId)
                .compose(new DefaultTransform<>());
    }

    /**
     * 完善该用户信息
     */
    public Observable<Response> saveClientInfo(int projectId, String clientName, String phone, int province,
                                               int city, int district, String detailAddr) {
        return ServicesClient.getServices().saveClientInfo(projectId, clientName, phone, province, city, district, detailAddr)
                .compose(new DefaultTransform<>());
    }

    public Observable<OrderResponse> getOrderList(int custId, String search, int state) {
        User user = UserModel.getInstance().getUser();
        return ServicesClient.getServices().orderList(user == null ? 0 : user.getUserId(), custId, search, state)
                .compose(new DefaultTransform<>());
    }

    /**
     * 我的售后列表
     *
     * @return
     */
    public Observable<OrderResponse> getServiceList() {
        User user = UserModel.getInstance().getUser();
        return ServicesClient.getServices().servicesList(user == null ? 0 : user.getUserId())
                .compose(new DefaultTransform<>());
    }

    /**
     * 我的售后详情
     *
     * @return
     */
    public Observable<OrderResponse> getServiceDetail(int orderId) {
        return ServicesClient.getServices().servicesDetail(orderId)
                .compose(new DefaultTransform<>());
    }

    /**
     * 订单详情（房间）
     *
     * @param custId
     * @param projectId
     * @return
     */
    public Observable<RoomOrder> getOrderDetail(int custId, int projectId) {
        return ServicesClient.getServices().orderDetail(custId, projectId)
                .compose(new DefaultTransform<>());
    }

    public Observable<Order> createOrder(int custId, int projectId, String type) {
        return ServicesClient.getServices().createOrder(custId, projectId, type)
                .compose(new DefaultTransform<>());
    }

    public Observable<Project> addRoomNum(int projectId, int roomNum) {
        return ServicesClient.getServices().addRoomNum(projectId, roomNum)
                .compose(new DefaultTransform<>());
    }

    /**
     * 更换温控器
     * @param projectId
     * @param qyt
     * @param spec
     * @return
     */
    public Observable<Project> changeHeat(int projectId, int qyt, String spec) {
        return ServicesClient.getServices().changeHeat(projectId, qyt, spec).compose(new DefaultTransform<>());
    }

    /**
     * @param addArea
     * @param addStatus
     * @param itemId
     * @param projectId
     * @param roomName
     * @param roomSize
     * @param roomType  标准房间传1，不规则房间传0
     * @return
     */
    public Observable<Project> saveRoomParams(String addArea, String reduceArea, String addStatus,
                                              int itemId, int projectId, String roomName, String roomSize, int roomType, int melType) {
        return ServicesClient.getServices().saveRoomParams(addArea, reduceArea, addStatus, itemId,
                projectId, roomName, roomSize, roomType, melType)
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

    public Observable<Response> updateOrder(String order) {
        return ServicesClient.getServices().updateOrder(order).compose(new DefaultTransform<>());
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
