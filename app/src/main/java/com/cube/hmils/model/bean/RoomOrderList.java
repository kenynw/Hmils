package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RoomOrderList implements Parcelable {

    public static final Creator<RoomOrderList> CREATOR = new Creator<RoomOrderList>() {
        @Override
        public RoomOrderList createFromParcel(Parcel source) {
            return new RoomOrderList(source);
        }

        @Override
        public RoomOrderList[] newArray(int size) {
            return new RoomOrderList[size];
        }
    };
    public List<RoomOrder> roomOrder;

    public RoomOrderList() {
    }

    protected RoomOrderList(Parcel in) {
        this.roomOrder = in.createTypedArrayList(RoomOrder.CREATOR);
    }

    public List<RoomOrder> getRoomOrder() {
        return roomOrder;
    }

    public void setRoomOrder(List<RoomOrder> roomOrder) {
        this.roomOrder = roomOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.roomOrder);
    }
}
