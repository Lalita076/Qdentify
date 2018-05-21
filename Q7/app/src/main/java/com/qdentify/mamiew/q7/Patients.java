package com.qdentify.mamiew.q7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class Patients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        CardView patientCard = (CardView)findViewById(R.id.patient_card);
        patientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Patients.this, "Click on card", Toast.LENGTH_LONG).show();
                Intent infoPatient = new Intent(Patients.this,PatientInfo.class);
                startActivity(infoPatient);
            }
        });
    }
}
