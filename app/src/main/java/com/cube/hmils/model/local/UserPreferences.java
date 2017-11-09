package com.cube.hmils.model.local;

import com.cube.hmils.utils.LUtils;

/**
 * Copyright (c) 2017/2/23. LiaoPeiKun Inc. All rights reserved.
 */

public class UserPreferences {

    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_AGENT_ID = "agentId";

    public static String getToken() {
        return getString(KEY_TOKEN);
    }

    public static void setToken(String token) {
        setString(KEY_TOKEN, token);
    }

    public static int getUserID() {
        return LUtils.getPreferences().getInt(KEY_USER_ID, 0);
    }

    public static void setUserID(int value) {
        LUtils.getPreferences().edit().putInt(KEY_USER_ID, value).apply();
    }

    public static int getAgentID() {
        return LUtils.getPreferences().getInt(KEY_AGENT_ID, 0);
    }

    public static void setAgentID(int value) {
        LUtils.getPreferences().edit().putInt(KEY_AGENT_ID, value).apply();
    }

    private static String getString(String key) {
        return LUtils.getPreferences().getString(key, "").trim();
    }

    private static void setString(String key, String value) {
        LUtils.getPreferences().edit().putString(key, value).apply();
    }
}

