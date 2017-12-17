package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.cube.hmils.model.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carol on 2017/11/19.
 */

public class OrderResponse implements Parcelable {

    private ArrayList<Service> orderList; // 售后列表

    private List<Order> custOrderList;

    private Service orderInfo;

    public ArrayList<Service> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Service> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getCustOrderList() {
        return custOrderList;
    }

    public void setCustOrderList(List<Order> custOrderList) {
        this.custOrderList = custOrderList;
    }

    public OrderResponse() {
    }

    public Service getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Service orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.orderList);
        dest.writeTypedList(this.custOrderList);
        dest.writeParcelable(this.orderInfo, flags);
    }

    protected OrderResponse(Parcel in) {
        this.orderList = in.createTypedArrayList(Service.CREATOR);
        this.custOrderList = in.createTypedArrayList(Order.CREATOR);
        this.orderInfo = in.readParcelable(Service.class.getClassLoader());
    }

    public static final Creator<OrderResponse> CREATOR = new Creator<OrderResponse>() {
        @Override
        public OrderResponse createFromParcel(Parcel source) {
            return new OrderResponse(source);
        }

        @Override
        public OrderResponse[] newArray(int size) {
            return new OrderResponse[size];
        }
    };
}
