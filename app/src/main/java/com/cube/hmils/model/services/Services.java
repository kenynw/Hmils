package com.cube.hmils.model.services;


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

    @FormUrlEncoded
    @POST("operator/login")
    Observable<User> login(
            @Field("userName") String userName,
            @Field("passWd") String passWd
    );

    @FormUrlEncoded
    @POST("operator/getValidateCode/")
    Observable<Response> sendCode(
            @Field("mark") int mark,
            @Field("telPhone") String telPhone
    );

    @FormUrlEncoded
    @POST("operator/checkValidateCode/")
    Observable<User> checkCode(
            @Field("phoneNum") String phoneNum,
            @Field("validateCode") String code
    );

    @FormUrlEncoded
    @POST("operator/changePwd/")
    Observable<Response> changePwd(
            @Field("userId") int userId,
            @Field("passWord") String passWord
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

}
