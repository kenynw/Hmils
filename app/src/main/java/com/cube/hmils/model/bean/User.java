package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carol on 2017/10/29.
 */

public class User implements Parcelable {
    private int agentId;

    private String token;

    private int userId;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.agentId);
        dest.writeString(this.token);
        dest.writeInt(this.userId);
    }

    protected User(Parcel in) {
        this.agentId = in.readInt();
        this.token = in.readString();
        this.userId = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
