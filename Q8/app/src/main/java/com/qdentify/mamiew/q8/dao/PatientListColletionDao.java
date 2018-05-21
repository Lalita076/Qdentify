package com.qdentify.mamiew.q8.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientListColletionDao implements Parcelable{
    @SerializedName("patient")      private  List<PatientListDao> data;

    public PatientListColletionDao() {
    }

    protected PatientListColletionDao(Parcel in) {
        data = in.createTypedArrayList(PatientListDao.CREATOR);
    }

    public static final Creator<PatientListColletionDao> CREATOR = new Creator<PatientListColletionDao>() {
        @Override
        public PatientListColletionDao createFromParcel(Parcel in) {
            return new PatientListColletionDao(in);
        }

        @Override
        public PatientListColletionDao[] newArray(int size) {
            return new PatientListColletionDao[size];
        }
    };

    public List<PatientListDao> getData() {
        return data;
    }

    public void setData(List<PatientListDao> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }

    /*@SerializedName("id_person")    private String idPerson;
    @SerializedName("data")         private List<PatientListDao> data;

    public PatientListColletionDao(){

    }

    protected PatientListColletionDao(Parcel in) {
        idPerson = in.readString();
        data = in.createTypedArrayList(PatientListDao.CREATOR);
    }

    public static final Creator<PatientListColletionDao> CREATOR = new Creator<PatientListColletionDao>() {
        @Override
        public PatientListColletionDao createFromParcel(Parcel in) {
            return new PatientListColletionDao(in);
        }

        @Override
        public PatientListColletionDao[] newArray(int size) {
            return new PatientListColletionDao[size];
        }
    };

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public List<PatientListDao> getData() {
        return data;
    }

    public void setData(List<PatientListDao> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idPerson);
        dest.writeTypedList(data);
    }
    */
}
