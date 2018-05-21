package com.qdentify.mamiew.q4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class view_patients extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients);
        toolbar = findViewById(R.id.bar_view_patients);
        setSupportActionBar(toolbar);
        toolbar.setTitle("View Patients");


    }

}
