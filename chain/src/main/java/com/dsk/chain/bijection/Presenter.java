package com.dsk.chain.bijection;

import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Presenter<ViewType> {
    /**
     * EventBus事件标识
     */
    public static final String EVENT_BUS_CODE = "event_bus_code";

    String mId;

    private ViewType view;

    public final ViewType getView() {
        return view;
    }

    public void create(ViewType view, Bundle saveState) {
        this.view = view;
        onCreate(view, saveState);
        EventBus.getDefault().register(this);
    }

    /**
     * activity 第一次create直到到主动退出Activity之前都不会调用。
     */
    protected void onCreate(ViewType view, Bundle saveState) {

    }

    /**
     * presenter销毁时的回调。代表着activity正式退出
     */
    protected void onDestroy() {
    }

    /**
     * activity$OnCreate的回调,但执行延迟到OnCreate之后。
     */
    protected void onCreateView(ViewType view) {
        this.view = view;
    }

    protected void onDestroyView() {
        EventBus.getDefault().unregister(this);
    }

    protected void onResume() {
    }

    protected void onPause() {
    }

    protected void onSave(Bundle state) {
    }

    protected void onResult(int requestCode, int resultCode, Intent data) {
    }

    protected void onNewIntent(Intent intent) {

    }

    /**
     * eventBus可以重写，用来处理接收消息，
     * @param event
     */
    @Subscribe
    public void onEventMainThread(Object event) {
        if (event instanceof Bundle){
            int eventCode = ((Bundle) event).getInt(EVENT_BUS_CODE);
            onEventMainThread(eventCode, (Bundle) event);
        }
    }

    /**
     * eventBus可以重写，用来处理接收消息，
     * @param eventCode
     */
    public void onEventMainThread(int eventCode, Bundle bundle) {
    }

}
