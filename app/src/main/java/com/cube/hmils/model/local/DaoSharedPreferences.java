

package com.cube.hmils.model.local;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.cube.hmils.model.bean.Params;
import com.cube.hmils.model.bean.User;
import com.cube.hmils.utils.GsonUtil;
import com.cube.hmils.utils.LUtils;

/**
 * SharedPreferences储存
 *
 * @author liyu
 */
public class DaoSharedPreferences {

    private static DaoSharedPreferences sInstance;

    private SharedPreferences mPreferences;

    private Editor mEditor;

    private static final String NAME = "hmilsDb";

    private static final String USER_ENTITY = "user_entity";

    private static final String ROOM_PARAMS_ENTITY = "room_params_info"; //是否第一次点击直播按钮

    private DaoSharedPreferences() {
        mPreferences = LUtils.getAppContext().getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static DaoSharedPreferences getInstance() {
        if (sInstance == null) {
            sInstance = new DaoSharedPreferences();
        }

        return sInstance;
    }

    public void setUser(User user) {
        if (user == null) return;
        mEditor.putString(USER_ENTITY, GsonUtil.toJson(user));
        mEditor.commit();
        LUtils.log("json: " + GsonUtil.toJson(user) + ", new: " + GsonUtil.toJson(getUser()));
    }

    public User getUser() {
        User user = null;
        String userStr = mPreferences.getString(USER_ENTITY, "");
        if (!TextUtils.isEmpty(userStr)) {
            user = GsonUtil.parse(userStr, User.class);
        }
        return user;
    }

    public void clearUser() {
        mEditor.putString(USER_ENTITY, "");
        mEditor.commit();
    }

    /**
     * 获取参数
     */
    public Params getRoomParams(String suffix) {
        Params params = null;
        String paramsStr = mPreferences.getString(ROOM_PARAMS_ENTITY + suffix, "");
        if (!TextUtils.isEmpty(paramsStr)) {
            params = GsonUtil.parse(paramsStr, Params.class);
        }
        return params;
    }

    /**
     * 设置用户信息
     */
    public void setRoomParams(Params params, String suffix) {
        if (params == null) {
            return;
        }
        mEditor.putString(ROOM_PARAMS_ENTITY + suffix, GsonUtil.toJson(params));
        mEditor.commit();
    }

}
