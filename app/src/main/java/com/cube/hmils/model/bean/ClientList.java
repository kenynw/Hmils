package com.cube.hmils.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Carol on 2017/11/12.
 */

public class ClientList implements Parcelable {

    private List<Client> custList;

    private List<Client> newCust;

    public List<Client> getCustList() {
        return custList;
    }

    public void setCustList(List<Client> custList) {
        this.custList = custList;
    }

    public List<Client> getNewCust() {
        return newCust;
    }

    public void setNewCust(List<Client> newCust) {
        this.newCust = newCust;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.custList);
        dest.writeTypedList(this.newCust);
    }

    public ClientList() {
    }

    protected ClientList(Parcel in) {
        this.custList = in.createTypedArrayList(Client.CREATOR);
        this.newCust = in.createTypedArrayList(Client.CREATOR);
    }

    public static final Creator<ClientList> CREATOR = new Creator<ClientList>() {
        @Override
        public ClientList createFromParcel(Parcel source) {
            return new ClientList(source);
        }

        @Override
        public ClientList[] newArray(int size) {
            return new ClientList[size];
        }
    };

}
