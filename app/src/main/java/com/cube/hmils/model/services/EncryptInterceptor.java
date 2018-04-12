package com.cube.hmils.model.services;


import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.utils.LUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Copyright (c) 2017/3/20. LiaoPeiKun Inc. All rights reserved.
 */

public class EncryptInterceptor implements Interceptor {

    private static final String TAG = EncryptInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        User user = UserModel.getInstance().getUser();
        Request request = chain.request().newBuilder()
                .addHeader("token", user == null ? "" : user.getToken())
                .addHeader("userId", String.valueOf(user == null ? 0 : user.getUserId()))
                .build();

        if (request.method().equals("POST")) {
            RequestBody requestBody = request.body();
            try {
                Buffer bufferedSink = new Buffer();
                requestBody.writeTo(bufferedSink);
                Charset charset = requestBody.contentType().charset();
                charset = charset == null ? Charset.forName("utf-8") : charset;
                LUtils.log(TAG, request.url() + "?" + bufferedSink.readString(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LUtils.log(TAG, request.url().toString());
        }

        return chain.proceed(request);
    }

}
