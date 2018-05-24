package com.qdentify.mamiew.q8.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qdentify.mamiew.q8.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LastTreatEditActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;

    private String date, hospital, doctor, symptom;
    private EditText etSymptom,etHospital,etDoctor;
    private TextView datePick;
    private Calendar mCurrrntDate;
    private int day, month, year;
    private DatabaseReference firebaseDatabase;

    private String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_treat_edit);

        toolbar = (Toolbar)findViewById(R.id.toolbar_last_treat_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Treat History");

        Intent intent = getIntent();
        patientId = intent.getStringExtra("patientId");
        date = intent.getStringExtra("date");
        hospital = intent.getStringExtra("hospital");
        doctor = intent.getStringExtra("doctor");
        symptom = intent.getStringExtra("symptom");


        initInstances();

        bindingData();
    }

    private void bindingData() {
            etSymptom.setText(symptom);
            etDoctor.setText(doctor);
            etHospital.setText(hospital);
            datePick.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ok) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initInstances(){
        etSymptom = (EditText) findViewById(R.id.et_symptom);
        etHospital = (EditText) findViewById(R.id.et_hospital);
        etDoctor = (EditText) findViewById(R.id.et_doctor_name);
        datePick = (TextView) findViewById(R.id.tv_date);

        mCurrrntDate = Calendar.getInstance();

        day = mCurrrntDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrrntDate.get(Calendar.MONTH);
        year = mCurrrntDate.get(Calendar.YEAR);

        datePick.setText(day + "/" + month + "/" + year);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("patient").child("data").child(patientId);

        datePick.setOnClickListener(this);

    }

    private void save() {
        String symptomUpdate = etSymptom.getText().toString().trim();
        String hospitalUpdate = etHospital.getText().toString().trim();
        String doctorUpdate = etDoctor.getText().toString().trim();
        String dateUpdate = datePick.getText().toString().trim();

        String newLastTreat = firebaseDatabase.push().getKey();

        Map<String, Object> map = new HashMap<>();
        map.put("symptom", symptomUpdate);
        map.put("hospital", hospitalUpdate);
        map.put("doctor",doctorUpdate);
        map.put("data",dateUpdate);
        firebaseDatabase.child("lastTreat").child(newLastTreat).setValue(map);

        Toast.makeText(this,"save",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == datePick) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(LastTreatEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    datePick.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }, year, month, day);
            datePickerDialog.show();
        }
    }
}
