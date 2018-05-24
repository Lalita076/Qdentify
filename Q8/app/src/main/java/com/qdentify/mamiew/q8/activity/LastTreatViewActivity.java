package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.qdentify.mamiew.q8.R;
import com.squareup.picasso.Picasso;

public class LastTreatViewActivity extends AppCompatActivity {
    private String key, date, hospital, doctor, symptom;
    private Toolbar toolbar;
    private TextView tvSymptom,tvHospital,tvDoctor,tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_treat_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar_last_treat);
        setSupportActionBar(toolbar);

        initInstances();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        date = intent.getStringExtra("date");
        hospital = intent.getStringExtra("hospital");
        doctor = intent.getStringExtra("doctor");
        symptom = intent.getStringExtra("symptom");

        getSupportActionBar().setTitle(date);

        bindingData();
    }

    private void initInstances() {
        tvSymptom = (TextView) findViewById(R.id.tv_symptom);
        tvHospital = (TextView) findViewById(R.id.tv_hospital);
        tvDoctor = (TextView) findViewById(R.id.tv_doctor_name);
        tvDate = (TextView) findViewById(R.id.tv_date);

    }

    private void bindingData() {
        tvSymptom.setText(symptom);
        tvHospital.setText(hospital);
        tvDoctor.setText(doctor);
        tvDate.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_last_treat_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent editLt = new Intent(this, LastTreatEditActivity.class);
            editLt.putExtra("patientId", key);
            editLt.putExtra("date", date);
            editLt.putExtra("hospital", hospital);
            editLt.putExtra("doctor", doctor);
            editLt.putExtra("symptom", symptom);
            startActivity(editLt);
            return true;
        }
        else if (id==R.id.action_delete){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
