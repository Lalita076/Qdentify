package com.qdentify.mamiew.q2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ViewPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpatient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.bar_viewpatients);
        


    }
}
