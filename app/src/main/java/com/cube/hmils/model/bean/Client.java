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
     * city : 测试内容11yw
     * creatTime : 测试内容xt2b
     * custId : 25728
     * custImg : 测试内容5515
     * custName : 测试内容cqn4
     * detailAddr : 测试内容28uv
     * district : 测试内容x9z6
     * phoneNo : 测试内容xy84
     * projectId : 42722
     * province : 测试内容7ov5
     * street : 测试内容55bx
     */
    private String contTel;
    private int custId;
    private String custName;
    private int projectId;
    private String city;
    private String creatTime;
    private String custImg;
    private String detailAddr;
    private String district;
    private String phoneNo;
    private String province;
    private String street;
    private int custType;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getCustImg() {
        return custImg;
    }

    public void setCustImg(String custImg) {
        this.custImg = custImg;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCustType() {
        return custType;
    }

    public void setCustType(int custType) {
        this.custType = custType;
    }

    public String getFullAddress() {
        StringBuilder builder = new StringBuilder();
        builder.append(getProvince() == null ? "" : getProvince());
        builder.append(getCity() == null ? "" : getCity());
        builder.append(getDistrict() == null ? "" : getDistrict());
        builder.append(getDetailAddr() == null ? "" : getDetailAddr());
        return builder.toString();
    }

    public Client() {
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
        dest.writeInt(this.projectId);
        dest.writeString(this.city);
        dest.writeString(this.creatTime);
        dest.writeString(this.custImg);
        dest.writeString(this.detailAddr);
        dest.writeString(this.district);
        dest.writeString(this.phoneNo);
        dest.writeString(this.province);
        dest.writeString(this.street);
        dest.writeInt(this.custType);
    }

    protected Client(Parcel in) {
        this.contTel = in.readString();
        this.custId = in.readInt();
        this.custName = in.readString();
        this.projectId = in.readInt();
        this.city = in.readString();
        this.creatTime = in.readString();
        this.custImg = in.readString();
        this.detailAddr = in.readString();
        this.district = in.readString();
        this.phoneNo = in.readString();
        this.province = in.readString();
        this.street = in.readString();
        this.custType = in.readInt();
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
