package com.qdentify.mamiew.q8.dao;

public class PatientDataFB {
    String key;
    PatientListModelFB patientList;

    public PatientDataFB() {
    }

    public PatientDataFB(String key, PatientListModelFB patientList) {
        this.key = key;
        this.patientList = patientList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PatientListModelFB getPatientList() {
        return patientList;
    }

    public void setPatientList(PatientListModelFB patientList) {
        this.patientList = patientList;
    }
}
