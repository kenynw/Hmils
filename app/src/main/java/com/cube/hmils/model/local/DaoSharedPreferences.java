
package com.cube.hmils.model.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.cube.hmils.base.App;
import com.cube.hmils.model.bean.Params;
import com.cube.hmils.utils.GsonUtil;

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

    private static final String ROOM_PARAMS_ENTITY = "room_params_info"; //是否第一次点击直播按钮

    private DaoSharedPreferences() {
        mPreferences = App.getInstance().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static DaoSharedPreferences getInstance() {
        if (sInstance == null) {
            sInstance = new DaoSharedPreferences();
        }

        return sInstance;
    }

    /**
     * 获取参数
     */
    public Params getRoomParams(int position) {
        Params params = null;
        String paramsStr = mPreferences.getString(ROOM_PARAMS_ENTITY + position, "");
        if (!TextUtils.isEmpty(paramsStr)) {
            params = GsonUtil.parse(paramsStr, Params.class);
        }
        return params;
    }

    /**
     * 设置用户信息
     */
    public void setRoomParams(Params params, int position) {
        if (params == null) {
            return;
        }
        mEditor.putString(ROOM_PARAMS_ENTITY + position, GsonUtil.toJson(params));
        mEditor.commit();
    }

}
