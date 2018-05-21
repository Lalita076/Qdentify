package com.qdentify.mamiew.q8.dao;

import android.os.Parcel;
import android.os.Parcelable;

public class PurchaseObject implements Parcelable{

    private String pID, name, tagType, paymentMethod, shippingMethod, address;
    private int total;

    public PurchaseObject(){

    }


    protected PurchaseObject(Parcel in) {
        pID = in.readString();
        name = in.readString();
        tagType = in.readString();
        paymentMethod = in.readString();
        shippingMethod = in.readString();
        address = in.readString();
        total = in.readInt();
    }

    public static final Creator<PurchaseObject> CREATOR = new Creator<PurchaseObject>() {
        @Override
        public PurchaseObject createFromParcel(Parcel in) {
            return new PurchaseObject(in);
        }

        @Override
        public PurchaseObject[] newArray(int size) {
            return new PurchaseObject[size];
        }
    };

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pID);
        dest.writeString(name);
        dest.writeString(tagType);
        dest.writeString(paymentMethod);
        dest.writeString(shippingMethod);
        dest.writeString(address);
        dest.writeInt(total);
    }
}
