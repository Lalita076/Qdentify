package com.qdentify.mamiew.q8.dao;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientListModelFB {
    public String firstName, lastName, contact, bloodType, dob, disease, key, thumbnail, regDosing, drugAllergy, hospitalName, status;

    public PatientListModelFB() {
    }

    public PatientListModelFB(String firstName, String lastName, String contact, String bloodType, String dob, String disease, String key, String thumbnail, String regDosing, String drugAllergy, String hospitalName, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.bloodType = bloodType;
        this.dob = dob;
        this.disease = disease;
        this.key = key;
        this.thumbnail = thumbnail;
        this.regDosing = regDosing;
        this.drugAllergy = drugAllergy;
        this.hospitalName = hospitalName;
        this.status = status;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> result = new HashMap<>();
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("contact", contact);
        result.put("bloodType", bloodType);
        result.put("dob", dob);
        result.put("disease", disease);
        result.put("thumbnail", thumbnail);
        result.put("key", key);
        result.put("regDosing", regDosing);
        result.put("drugAllergy", drugAllergy);
        result.put("hospitalName", hospitalName);
        result.put("status", status);
        return result;
    }
}
