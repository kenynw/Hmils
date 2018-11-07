package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomOrderRes implements Parcelable {

    public static final Creator<RoomOrderRes> CREATOR = new Creator<RoomOrderRes>() {
        @Override
        public RoomOrderRes createFromParcel(Parcel source) {
            return new RoomOrderRes(source);
        }

        @Override
        public RoomOrderRes[] newArray(int size) {
            return new RoomOrderRes[size];
        }
    };
    public RoomOrder roomOrder;

    public RoomOrderRes() {
    }

    protected RoomOrderRes(Parcel in) {
        this.roomOrder = in.readParcelable(RoomOrder.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.roomOrder, flags);
    }

}
