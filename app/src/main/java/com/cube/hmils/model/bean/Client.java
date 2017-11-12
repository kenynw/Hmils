package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Carol on 2017/10/11.
 */

public class Client implements Parcelable {
    /**
     * contTel : 测试内容501u
     * custId : 81330
     * custName : 测试内容lf18
     * projectId : 测试内容usp7
     */

    private String contTel;
    private int custId;
    private String custName;
    private String projectId;

    public String getContTel() {
        return contTel;
    }

    public void setContTel(String contTel) {
        this.contTel = contTel;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contTel);
        dest.writeInt(this.custId);
        dest.writeString(this.custName);
        dest.writeString(this.projectId);
    }

    public Client() {
    }

    protected Client(Parcel in) {
        this.contTel = in.readString();
        this.custId = in.readInt();
        this.custName = in.readString();
        this.projectId = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
