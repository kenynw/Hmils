package com.cube.hmils.model.constant;

/**
 * Copyright (c) 2017/11/20. LiaoPeiKun Inc. All rights reserved.
 */

public interface EventCode {

    /**
     * 退出登录
     */
    int TO_HOME = 0x02;

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

    /**
     * 刷新个人中心
     */
    int CODE_ME_UPDATE = 0x05;

    /**
     * 刷新订单详情
     */
    int ORDER_DETAIL_UPDATE = 0x06;

    /**
     * 关闭参数详情页
     */
    int PARAM_DETAIL_FINISH = 0x07;

    /**
     * 刷新参数详情页
     */
    int PARAM_DETAIL_UPDATE = 0x08;

    /**
     * 初始化推送
     */
    int INIT_PUSH = 0x09;

    /**
     * 关闭我的二维码页面
     */
    int CODE_FINISH = 0x09;

    int SET_ORDER_LIST = 0x10;

}
