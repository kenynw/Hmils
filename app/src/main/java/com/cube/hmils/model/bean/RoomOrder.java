package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Carol on 2017/11/22.
 */

public class RoomOrder implements Parcelable {
    /**
     * hGoods : 85771
     * heatingList : [{"powerRating":"测试内容s134","price":"测试内容7474","qty":40318,"spec":"测试内容41p5"}]
     * itemId : 88832
     * mGoods : 54643
     * materialList : [{"powerRating":"测试内容rc33","price":"测试内容if8q","qty":67318,"spec":"测试内容l2ge"}]
     * roomName : 测试内容mg2l
     * totalGoods : 16251
     * totalPrice : 测试内容2t8l
     */

    private RoomOrder roomOrder;
    @Expose
    private List<RoomOrder> roomPara;
    private int hGoods;
    @Expose
    private int itemId;
    private int mGoods;
    private String roomName;
    private int totalGoods;
    private String totalPrice;
    private String custPhone;
    @Expose
    @SerializedName(value = "heatingList", alternate = "HeatingList")
    private List<Device> heatingList;
    @Expose
    @SerializedName(value = "material", alternate = "materialList")
    private List<Device> materialList;
    private InstallInfo installInfo;

    public RoomOrder getRoomOrder() {
        return roomOrder;
    }

    public void setRoomOrder(RoomOrder roomOrder) {
        this.roomOrder = roomOrder;
    }

    public List<RoomOrder> getRoomPara() {
        return roomPara;
    }

    public void setRoomPara(List<RoomOrder> roomPara) {
        this.roomPara = roomPara;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public int getHGoods() {
        return hGoods;
    }

    public void setHGoods(int hGoods) {
        this.hGoods = hGoods;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMGoods() {
        return mGoods;
    }

    public void setMGoods(int mGoods) {
        this.mGoods = mGoods;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getTotalGoods() {
        return totalGoods;
    }

    public void setTotalGoods(int totalGoods) {
        this.totalGoods = totalGoods;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Device> getHeatingList() {
        return heatingList;
    }

    public void setHeatingList(List<Device> heatingList) {
        this.heatingList = heatingList;
    }

    public List<Device> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Device> materialList) {
        this.materialList = materialList;
    }

    public InstallInfo getInstallInfo() {
        return installInfo;
    }

    public void setInstallInfo(InstallInfo installInfo) {
        this.installInfo = installInfo;
    }

    public RoomOrder() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.roomOrder, flags);
        dest.writeTypedList(this.roomPara);
        dest.writeInt(this.hGoods);
        dest.writeInt(this.itemId);
        dest.writeInt(this.mGoods);
        dest.writeString(this.roomName);
        dest.writeInt(this.totalGoods);
        dest.writeString(this.totalPrice);
        dest.writeString(this.custPhone);
        dest.writeTypedList(this.heatingList);
        dest.writeTypedList(this.materialList);
        dest.writeParcelable(this.installInfo, flags);
    }

    protected RoomOrder(Parcel in) {
        this.roomOrder = in.readParcelable(RoomOrder.class.getClassLoader());
        this.roomPara = in.createTypedArrayList(RoomOrder.CREATOR);
        this.hGoods = in.readInt();
        this.itemId = in.readInt();
        this.mGoods = in.readInt();
        this.roomName = in.readString();
        this.totalGoods = in.readInt();
        this.totalPrice = in.readString();
        this.custPhone = in.readString();
        this.heatingList = in.createTypedArrayList(Device.CREATOR);
        this.materialList = in.createTypedArrayList(Device.CREATOR);
        this.installInfo = in.readParcelable(InstallInfo.class.getClassLoader());
    }

    public static final Creator<RoomOrder> CREATOR = new Creator<RoomOrder>() {
        @Override
        public RoomOrder createFromParcel(Parcel source) {
            return new RoomOrder(source);
        }

        @Override
        public RoomOrder[] newArray(int size) {
            return new RoomOrder[size];
        }
    };
}
