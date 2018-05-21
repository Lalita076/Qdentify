package com.qdentify.mamiew.q8.manager.http;

import com.qdentify.mamiew.q8.dao.PatientListColletionDao;
import com.qdentify.mamiew.q8.dao.PatientListDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    //String BASE_URL = "https://api.myjson.com/bins/";
    //String BASE_URL = "https://raw.githubusercontent.com/Lalita076/Qdentify/master/";
    String BASE_URL = "https://data-e5cd3.firebaseio.com/";

    //@GET("sgmni")
    //@GET("example_data.json")
    @GET("patient.json")
    Call<PatientListColletionDao> loadPatientList();
}
