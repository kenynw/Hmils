package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2018 ** Inc All rights reserved.
 * Created by Liaopeikun on 2018/1/18
 */

public class Params implements Parcelable {

    private String mName;

    private List<Room> mRooms;

    private List<Room> mAddAreas; // 增加面积

    private List<Room> mMinuAreas; // 减少面积

    private int mIsSteady; // 是否规则

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Room> getRooms() {
        return mRooms;
    }

    public void setRooms(List<Room> rooms) {
        mRooms = rooms;
    }

    public List<Room> getAddAreas() {
        return mAddAreas;
    }

    public void setAddAreas(List<Room> addAreas) {
        mAddAreas = addAreas;
    }

    public List<Room> getMinuAreas() {
        return mMinuAreas;
    }

    public void setMinuAreas(List<Room> minuAreas) {
        mMinuAreas = minuAreas;
    }

    public int getIsSteady() {
        return mIsSteady;
    }

    public void setIsSteady(int isSteady) {
        mIsSteady = isSteady;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeTypedList(this.mRooms);
        dest.writeTypedList(this.mAddAreas);
        dest.writeTypedList(this.mMinuAreas);
        dest.writeInt(this.mIsSteady);
    }

    public Params() {
    }

    protected Params(Parcel in) {
        this.mName = in.readString();
        this.mRooms = in.createTypedArrayList(Room.CREATOR);
        this.mAddAreas = in.createTypedArrayList(Room.CREATOR);
        this.mMinuAreas = in.createTypedArrayList(Room.CREATOR);
        this.mIsSteady = in.readInt();
    }

    public static final Creator<Params> CREATOR = new Creator<Params>() {
        @Override
        public Params createFromParcel(Parcel source) {
            return new Params(source);
        }

        @Override
        public Params[] newArray(int size) {
            return new Params[size];
        }
    };
}
