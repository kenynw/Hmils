package com.cube.hmils.model.services;


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
            @Field("telPhone") String telPhone,
            @Field("passWd") String passWd
    );

    @FormUrlEncoded
    @POST("operator/getValidateCode/")
    Observable<String> sendCode(
            @Field("mark") String mark,
            @Field("telPhone") String telPhone
    );

    /**
     *
     * @param nameTel 客户名/用户电话
     * @param userId 用户Id
     * @return
     */
    @FormUrlEncoded
    @POST("operator/getCustList/")
    Observable<String> sendCode(
            @Field("nameTel") String nameTel,
            @Field("userId") int userId
    );

}
