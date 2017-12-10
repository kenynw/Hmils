package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by Carol on 2017/12/10.
 */

public class Dist implements IPickerViewData, Parcelable {

    private int distCode;

    private String distName;

    private int parentCode;

    public int getDistCode() {
        return distCode;
    }

    public void setDistCode(int distCode) {
        this.distCode = distCode;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.distCode);
        dest.writeString(this.distName);
        dest.writeInt(this.parentCode);
    }

    public Dist() {
    }

    protected Dist(Parcel in) {
        this.distCode = in.readInt();
        this.distName = in.readString();
        this.parentCode = in.readInt();
    }

    public static final Creator<Dist> CREATOR = new Creator<Dist>() {
        @Override
        public Dist createFromParcel(Parcel source) {
            return new Dist(source);
        }

        @Override
        public Dist[] newArray(int size) {
            return new Dist[size];
        }
    };

    @Override
    public String getPickerViewText() {
        return distName;
    }
}
