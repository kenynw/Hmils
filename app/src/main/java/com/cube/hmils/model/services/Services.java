package com.cube.hmils.model.services;


import com.cube.hmils.model.bean.Client;
import com.cube.hmils.model.bean.ClientList;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public interface Services {

    String BASE_URL = "http://106.14.116.138:8090/hms-api/";

    /**
     * 登录
     * @param userName 用户名
     * @param passWd 密码
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
     * @param mark 0
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
     * @param phoneNum 电话
     * @param code 验证码
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
     * @param userId 用户ID
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
     * @param userId 用户ID
     * @return
     */
    @FormUrlEncoded
    @POST("operator/editUserInfo/")
    Observable<User> editUserInfo(
            @Field("userId") int userId,
            @Field("telPhone") int telPhone,
            @Field("userName") int userName
    );

    /**
     * 客户列表
     *
     * @param nameTel 客户名/用户电话
     * @param userId 用户Id
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
     * @param custId 客户Id
     * @param projectId 项目Id
     * @param userId 用户Id
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getCustDetail/")
    Observable<Client> getClientDetail(
            @Field("userId") int userId,
            @Field("custId") int custId,
            @Field("projectId") int projectId
    );

}
