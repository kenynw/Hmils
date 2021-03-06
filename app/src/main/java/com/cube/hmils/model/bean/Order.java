package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Carol on 2017/10/14.
 */

public class Order implements Parcelable {

    @SerializedName(alternate = "projectId", value = "project")
    private int projectId;

    private int orderId;

    private String orderTime;

    private String custName;

    private String custTel;

    private String custAddr;

    private int handingStatus;

    private String appoTime; //上门时间

    private String procCode; //处理环节代码

    private String time; //指派时间

    private int prCode;

    public String prCodeName;

    private ArrayList<Device> heatList; // 温控器类型

    public int getProjectId() {
        return projectId;
    }

    public String getAppoTime() {
        return appoTime;
    }

    public void setAppoTime(String appoTime) {
        this.appoTime = appoTime;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public int getHandingStatus() {
        return handingStatus;
    }

    public void setHandingStatus(int handingStatus) {
        this.handingStatus = handingStatus;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Device> getHeatList() {
        return heatList;
    }

    public void setHeatList(ArrayList<Device> heatList) {
        this.heatList = heatList;
    }

    protected Order(Parcel in) {
        this.projectId = in.readInt();
        this.orderId = in.readInt();
        this.orderTime = in.readString();
        this.custName = in.readString();
        this.custTel = in.readString();
        this.custAddr = in.readString();
        this.handingStatus = in.readInt();
        this.appoTime = in.readString();
        this.procCode = in.readString();
        this.time = in.readString();
        this.prCode = in.readInt();
        this.prCodeName = in.readString();
        this.heatList = in.createTypedArrayList(Device.CREATOR);
    }

    public int getPrCode() {
        return prCode;
    }

    public Order() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setPrCode(int prCode) {
        this.prCode = prCode;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.projectId);
        dest.writeInt(this.orderId);
        dest.writeString(this.orderTime);
        dest.writeString(this.custName);
        dest.writeString(this.custTel);
        dest.writeString(this.custAddr);
        dest.writeInt(this.handingStatus);
        dest.writeString(this.appoTime);
        dest.writeString(this.procCode);
        dest.writeString(this.time);
        dest.writeInt(this.prCode);
        dest.writeString(this.prCodeName);
        dest.writeTypedList(this.heatList);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
