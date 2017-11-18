package com.cube.hmils.model.services;

import android.util.Log;

import com.cube.hmils.utils.LUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class WrapperResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final String TAG = "WrapperResponseBodyConverter";

    private final Type mType;

    WrapperResponseBodyConverter(Type type) {
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String result = value.string();

            JSONObject data = new JSONObject(result);

            LUtils.log(TAG, data.toString());

            if (data.has("status")) {
                int status = data.getInt("status");
                if (status != 200) {
                    throw new ServiceException(status, data.getString("message"));
                }
            }

            if (data.has("data")) {
                if (!data.isNull("data")) result = data.opt("data").toString();
                else return new Gson().fromJson(data.toString(), mType);
            } else {
                return new Gson().fromJson(data.toString(), mType);
            }

            return new Gson().fromJson(result, mType);
        } catch (JSONException | JsonSyntaxException e) {
            LUtils.log(Log.getStackTraceString(e));
            throw new ServiceException(0, "数据解析错误");
        }
    }

}
