package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carol on 2017/11/30.
 */

public class InstallInfo implements Parcelable {

    private String appoTime;

    private String mobile;

    private String name;

    private int orderCode;

    private String orderStatus;

    public String getAppoTime() {
        return appoTime;
    }

    public void setAppoTime(String appoTime) {
        this.appoTime = appoTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public InstallInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appoTime);
        dest.writeString(this.mobile);
        dest.writeString(this.name);
        dest.writeInt(this.orderCode);
        dest.writeString(this.orderStatus);
    }

    protected InstallInfo(Parcel in) {
        this.appoTime = in.readString();
        this.mobile = in.readString();
        this.name = in.readString();
        this.orderCode = in.readInt();
        this.orderStatus = in.readString();
    }

    public static final Creator<InstallInfo> CREATOR = new Creator<InstallInfo>() {
        @Override
        public InstallInfo createFromParcel(Parcel source) {
            return new InstallInfo(source);
        }

        @Override
        public InstallInfo[] newArray(int size) {
            return new InstallInfo[size];
        }
    };
}
