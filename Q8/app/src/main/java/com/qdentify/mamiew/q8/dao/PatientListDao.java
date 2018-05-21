package com.qdentify.mamiew.q8.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PatientListDao implements Parcelable {

    @SerializedName("patient_id")       private String pID;
    @SerializedName("first_name")       private String firstName;
    @SerializedName("last_name")        private String lastName;
    @SerializedName("date_of_birth")    private String dob;
    @SerializedName("blood_type")       private String bloodType;
    @SerializedName("disease")          private List<String> disease ;
    @SerializedName("regular_dosing")   private List<String> regDosing;
    @SerializedName("drug_allergies")   private List<String> drugAllergies;
    @SerializedName("hospital")         private String hospital;
    @SerializedName("contact")          private String contact;

    public PatientListDao(){

    }

    protected PatientListDao(Parcel in) {
        pID = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        dob = in.readString();
        bloodType = in.readString();
        disease = in.createStringArrayList();
        regDosing = in.createStringArrayList();
        drugAllergies = in.createStringArrayList();
        hospital = in.readString();
        contact = in.readString();
    }

    public static final Creator<PatientListDao> CREATOR = new Creator<PatientListDao>() {
        @Override
        public PatientListDao createFromParcel(Parcel in) {
            return new PatientListDao(in);
        }

        @Override
        public PatientListDao[] newArray(int size) {
            return new PatientListDao[size];
        }
    };

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public List<String> getDisease() {
        return disease;
    }

    public void setDisease(List<String> disease) {
        this.disease = disease;
    }

    public List<String> getRegDosing() {
        return regDosing;
    }

    public void setRegDosing(List<String> regDosing) {
        this.regDosing = regDosing;
    }

    public List<String> getDrugAllergies() {
        return drugAllergies;
    }

    public void setDrugAllergies(List<String> drugAllergies) {
        this.drugAllergies = drugAllergies;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pID);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(dob);
        dest.writeString(bloodType);
        dest.writeStringList(disease);
        dest.writeStringList(regDosing);
        dest.writeStringList(drugAllergies);
        dest.writeString(hospital);
        dest.writeString(contact);
    }
}
