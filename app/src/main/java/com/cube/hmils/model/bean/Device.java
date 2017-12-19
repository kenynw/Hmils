package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by Carol on 2017/11/22.
 */

public class Device implements Parcelable {

    private String powerRating;

    private String price;

    @Expose
    private int qty;

    @Expose
    private String spec;

    public String getPowerRating() {
        return powerRating;
    }

    public void setPowerRating(String powerRating) {
        this.powerRating = powerRating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.powerRating);
        dest.writeString(this.price);
        dest.writeInt(this.qty);
        dest.writeString(this.spec);
    }

    public Device() {
    }

    protected Device(Parcel in) {
        this.powerRating = in.readString();
        this.price = in.readString();
        this.qty = in.readInt();
        this.spec = in.readString();
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel source) {
            return new Device(source);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    // 用于设置spanner
    @Override
    public String toString() {
        return spec;
    }

}
