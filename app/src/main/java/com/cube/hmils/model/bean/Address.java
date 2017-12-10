package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Carol on 2017/12/10.
 */

public class Address implements Parcelable {

    private List<Province> province;

    private List<City> city;

    private List<Dist> dist;

    public List<Province> getProvince() {
        return province;
    }

    public void setProvince(List<Province> province) {
        this.province = province;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public List<Dist> getDist() {
        return dist;
    }

    public void setDist(List<Dist> dist) {
        this.dist = dist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.province);
        dest.writeTypedList(this.city);
        dest.writeTypedList(this.dist);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.province = in.createTypedArrayList(Province.CREATOR);
        this.city = in.createTypedArrayList(City.CREATOR);
        this.dist = in.createTypedArrayList(Dist.CREATOR);
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
