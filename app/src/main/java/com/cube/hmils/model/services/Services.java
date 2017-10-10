package com.cube.hmils.model.services;


import com.cube.hmils.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public interface Services {

    String BASE_BETA_URL = LUtils.isDebug ? "http://m.beta.yjyapp.com/" : "http://m.yjyapp.com/";

    String BASE_URL = "https://api.yjyapp.com/api/index/";

    String DEBUG_BASE_URL = "http://api.beta.yjyapp.com/api/index/";

}
