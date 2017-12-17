package com.cube.hmils.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carol on 2017/12/17.
 */
public class Service implements Parcelable {

    private int orderId;

    private String codeName;

    private String contAddr;

    private String contTel;

    private String custName;

    private int procCode;

    private String time;

    private String orderCnt; // 售后问题

    private String memo; // 备注

    private String[] photo; // 图片数组

    private String installer; // 安装人员姓名

    private String phoneNo; // 	安装人员电话

    public String getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(String orderCnt) {
        this.orderCnt = orderCnt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String[] getPhoto() {
        return photo;
    }

    public void setPhoto(String[] photo) {
        this.photo = photo;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getContAddr() {
        return contAddr;
    }

    public void setContAddr(String contAddr) {
        this.contAddr = contAddr;
    }

    public String getContTel() {
        return contTel;
    }

    public void setContTel(String contTel) {
        this.contTel = contTel;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getProcCode() {
        return procCode;
    }

    public void setProcCode(int procCode) {
        this.procCode = procCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Service() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderId);
        dest.writeString(this.codeName);
        dest.writeString(this.contAddr);
        dest.writeString(this.contTel);
        dest.writeString(this.custName);
        dest.writeInt(this.procCode);
        dest.writeString(this.time);
        dest.writeString(this.orderCnt);
        dest.writeString(this.memo);
        dest.writeStringArray(this.photo);
        dest.writeString(this.installer);
        dest.writeString(this.phoneNo);
    }

    protected Service(Parcel in) {
        this.orderId = in.readInt();
        this.codeName = in.readString();
        this.contAddr = in.readString();
        this.contTel = in.readString();
        this.custName = in.readString();
        this.procCode = in.readInt();
        this.time = in.readString();
        this.orderCnt = in.readString();
        this.memo = in.readString();
        this.photo = in.createStringArray();
        this.installer = in.readString();
        this.phoneNo = in.readString();
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel source) {
            return new Service(source);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };
}
