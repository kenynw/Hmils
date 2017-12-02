package com.cube.hmils.model.services;


import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.ClientList;
import com.cube.hmils.model.bean.Order;
import com.cube.hmils.model.bean.OrderList;
import com.cube.hmils.model.bean.Project;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.RoomOrder;
import com.cube.hmils.model.bean.User;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public interface Services {

    String BASE_URL = "http://106.14.116.138:8090/hms-api/";

    /**
     * 每次启动都要调用一次才能显示正常的状态  我也不知道干嘛用的
     */
    @GET("operator/getMessage")
    Observable<Response> getMessage();

    /**
     * 登录
     *
     * @param userName 用户名
     * @param passWd   密码
     * @return
     */
    @FormUrlEncoded
    @POST("operator/login")
    Observable<User> login(
            @Field("userName") String userName,
            @Field("passWd") String passWd
    );

    /**
     * 发送验证码
     *
     * @param mark     0
     * @param telPhone 电话
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getValidateCode/")
    Observable<Response> sendCode(
            @Field("mark") int mark,
            @Field("telPhone") String telPhone
    );

    /**
     * 验证验证码是否正确
     *
     * @param phoneNum 电话
     * @param code     验证码
     * @return
     */
    @FormUrlEncoded
    @POST("operator/checkValidateCode/")
    Observable<User> checkCode(
            @Field("phoneNum") String phoneNum,
            @Field("validateCode") String code
    );

    /**
     * 更改密码
     *
     * @param userId   用户ID
     * @param passWord 密码
     * @return
     */
    @FormUrlEncoded
    @POST("operator/changePwd/")
    Observable<Response> changePwd(
            @Field("userId") int userId,
            @Field("passWord") String passWord
    );

    /**
     * 个人中心
     *
     * @param userId 用户ID
     * @return
     */
    @FormUrlEncoded
    @POST("operator/persionCenter/")
    Observable<User> userDetail(
            @Field("userId") int userId
    );

    /**
     * 更改用户资料
     * file	用户头像	object
     * telPhone	电话号码	string
     * userId	用户ID	number
     * userName	用户名称
     *
     * @return
     */
    @Multipart
    @POST("operator/editUserInfo/")
    Observable<Response> editUserInfo(
            @PartMap Map<String, RequestBody> params
    );

    /**
     * 客户订单列表
     *
     * @param nameTel 客户名/用户电话
     * @param userId  用户Id
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getOrderList/")
    Observable<OrderList> orderList(
            @Field("userId") int userId,
            @Field("nameTel") String nameTel,
            @Field("handingStatus") int state
    );

    /**
     * 订单详情
     *
     * @param custId    客户名/用户电话
     * @param projectId 项目Id
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getOrderDetail/")
    Observable<RoomOrder> orderDetail(
            @Field("custId") int custId,
            @Field("projectId") int projectId
    );

    /**
     * 修改订单详情
     */
    @FormUrlEncoded
    @POST("operator/updateOrder/")
    Observable<Response> updateOrder(
            @Field("updatePara") String updatePara
    );

    /**
     * 确认并发送给客户
     *
     * @param projectId 项目Id
     * @param payType   0---线上支付，1---线下支付
     * @return
     */
    @FormUrlEncoded
    @POST("operator/comfirmOrder/")
    Observable<RoomOrder> comfirmOrder(
            @Field("projectId") int projectId,
            @Field("payType") int payType
    );

    /**
     * 客户订单列表
     *
     * @param custId    客户名/用户电话
     * @param projectId 项目Id
     * @param busiType  类型
     * @return
     */
    @FormUrlEncoded
    @POST("operator/creatOrder/")
    Observable<Order> createOrder(
            @Field("custId") int custId,
            @Field("projectId") int projectId,
            @Field("busiType") int busiType
    );

    /**
     * 填写房间数量
     *
     * @return
     */
    @FormUrlEncoded
    @POST("operator/addProject/")
    Observable<Project> addRoomNum(
            @Field("projectId") int projectId,
            @Field("roomNum") int roomNum
    );

    /**
     * 填写房间数量
     *
     * @param roomType 标准房间传1，不规则房间传0
     * @return
     */
    @FormUrlEncoded
    @POST("operator/addRoomPara/")
    Observable<Project> saveRoomParams(
            @Field("addArea") int addArea,
            @Field("addStatus") String addStatus,
            @Field("itemId") int itemId,
            @Field("projectId") int projectId,
            @Field("roomName") String roomName,
            @Field("roomSize") String roomSize,
            @Field("roomType") int roomType
    );

    /**
     * 填写房间数量
     *
     * @param projectId
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getTotalOrder/")
    Observable<RoomOrder> getTotalOrder(
            @Field("projectId") int projectId
    );

    /**
     * 客户列表
     *
     * @param nameTel 客户名/用户电话
     * @param userId  用户Id
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getCustList/")
    Observable<ClientList> getCustList(
            @Field("userId") int userId,
            @Field("nameTel") String nameTel
    );

    /**
     * 客户列表
     *
     * @param custId    客户Id
     * @param projectId 项目Id
     * @param userId    用户Id
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getCustDetail/")
    Observable<Client> getClientDetail(
            @Field("userId") int userId,
            @Field("custId") int custId,
            @Field("projectId") int projectId
    );

    /**
     * 售后列表
     *
     * @param userId 用户Id
     */
    @FormUrlEncoded
    @POST("operator/afterSaleList/")
    Observable<OrderList> servicesList(
            @Field("userId") int userId,
            @Field("handingStatus") int state
    );

    /**
     * 完善/修改客户信息
     *
     * @param projectId  项目Id
     * @param custName   用户名
     * @param phone      手机号
     * @param province   省
     * @param city       市
     * @param district   县
     * @param detailAddr 详细地址
     * @return
     */
    @FormUrlEncoded
    @POST("operator/editCustInfo/")
    Observable<Response> saveClientInfo(
            @Field("projectId") int projectId,
            @Field("custName") String custName,
            @Field("phoneNo") String phone,
            @Field("province") String province,
            @Field("city") String city,
            @Field("district") String district,
            @Field("detailAddr") String detailAddr
    );

}
