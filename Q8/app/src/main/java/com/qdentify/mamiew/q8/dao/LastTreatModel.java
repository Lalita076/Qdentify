package com.qdentify.mamiew.q8.dao;

import java.util.HashMap;
import java.util.Map;

public class LastTreatModel {
    public String date, symptom, hospital, doctorName;

    public LastTreatModel() {
    }

    public LastTreatModel(String date, String symptom, String hospital, String doctorName) {
        this.date = date;
        this.symptom = symptom;
        this.hospital = hospital;
        this.doctorName = doctorName;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("doctorName", doctorName);
        result.put("symptom", symptom);
        result.put("hospital", hospital);
        return result;
    }
}
