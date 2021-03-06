package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Carol on 2017/11/19.
 */

public class Room implements Parcelable {

    @SerializedName("long")
    private String mLong;

    private String width;

    public String getLong() {
        return mLong;
    }

    public void setLong(String aLong) {
        this.mLong = aLong;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mLong);
        dest.writeString(this.width);
    }

    public Room() {
    }

    protected Room(Parcel in) {
        this.mLong = in.readString();
        this.width = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel source) {
            return new Room(source);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };
}
