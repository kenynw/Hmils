package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by Carol on 2017/12/10.
 */

public class City implements IPickerViewData, Parcelable {

    private int cityCode;

    private String cityName;

    private int parentCode;

    private List<Dist> distList;

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
    }

    public List<Dist> getDistList() {
        return distList;
    }

    public void setDistList(List<Dist> distList) {
        this.distList = distList;
    }

    public City() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cityCode);
        dest.writeString(this.cityName);
        dest.writeInt(this.parentCode);
        dest.writeTypedList(this.distList);
    }

    protected City(Parcel in) {
        this.cityCode = in.readInt();
        this.cityName = in.readString();
        this.parentCode = in.readInt();
        this.distList = in.createTypedArrayList(Dist.CREATOR);
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public String getPickerViewText() {
        return cityName;
    }
}
