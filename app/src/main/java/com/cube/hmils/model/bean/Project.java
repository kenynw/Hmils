package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carol on 2017/11/19.
 */

public class Project implements Parcelable {
    private int[] itemId;

    public int[] getItemId() {
        return itemId;
    }

    public void setItemId(int[] itemId) {
        this.itemId = itemId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.itemId);
    }

    public Project() {
    }

    protected Project(Parcel in) {
        this.itemId = in.createIntArray();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
