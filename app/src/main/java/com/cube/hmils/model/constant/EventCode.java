package com.cube.hmils.model.constant;

/**
 * Copyright (c) 2017/11/20. LiaoPeiKun Inc. All rights reserved.
 */

public interface EventCode {

    /**
     * 退出登录
     */
    int LOGOUT = 0x02;

    /**
     * 编辑地址
     */
    int EDIT_ADDRESS = 0x03;

    /**
     * 关闭房间参数页面
     */
    int ROOM_PARAMS_FINISH = 0x04;

    /**
     * 刷新订单列表
     */
    int ORDER_LIST_UPDATE = 0x05;

    /**
     * 刷新客户列表
     */
    int CLIENT_LIST_UPDATE = 0x05;

}