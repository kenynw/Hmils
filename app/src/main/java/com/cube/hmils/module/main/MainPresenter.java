package com.cube.hmils.module.main;

import android.os.Bundle;

import com.cube.hmils.model.UserModel;
import com.cube.hmils.model.bean.Response;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.model.constant.EventCode;
import com.cube.hmils.utils.LUtils;
import com.dsk.chain.bijection.Presenter;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Subscriber;

/**
 * Created by Carol on 2017/10/11.
 */

public class MainPresenter extends Presenter<MainActivity> {

    @Override
    protected void onCreate(MainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        UserModel.getInstance().getMessage().unsafeSubscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response response) {

            }
        });
    }

    private void initPush() {
        // 调用 JPush 接口来设置别名。
        User user = UserModel.getInstance().getUser();
        if (user != null) {
            JPushInterface.setAlias(getView(), user.getUserName(), new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {
                    LUtils.log("code: " + i + ", result : " + s + ", set: " + set);
                }
            });
        }
    }

    @Override
    public void onEventMainThread(int eventCode, Bundle bundle) {
        if (eventCode == EventCode.TO_HOME) {
            getView().setCurrentItem(0);
        } else if (eventCode == EventCode.INIT_PUSH) {
            initPush();
        }
    }

}
