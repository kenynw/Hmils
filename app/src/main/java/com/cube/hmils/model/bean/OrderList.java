package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Carol on 2017/11/19.
 */

public class OrderList implements Parcelable {
    private List<Order> custOrderList;

    public List<Order> getCustOrderList() {
        return custOrderList;
    }

    public void setCustOrderList(List<Order> custOrderList) {
        this.custOrderList = custOrderList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.custOrderList);
    }

    public OrderList() {
    }

    protected OrderList(Parcel in) {
        this.custOrderList = in.createTypedArrayList(Order.CREATOR);
    }

    public static final Creator<OrderList> CREATOR = new Creator<OrderList>() {
        @Override
        public OrderList createFromParcel(Parcel source) {
            return new OrderList(source);
        }

        @Override
        public OrderList[] newArray(int size) {
            return new OrderList[size];
        }
    };
}
