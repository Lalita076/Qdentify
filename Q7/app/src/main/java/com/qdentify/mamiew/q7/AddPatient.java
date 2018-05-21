package com.qdentify.mamiew.q7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatient extends AppCompatActivity {
    private EditText et_first_name, et_last_name, et_blood, et_dob, et_drug_allergy, et_reg_dose, et_hospital, et_contact;
    private String firstName, lastName, bloodType, dob, drugAllergy, regDose, hospital, contact;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        et_first_name = (EditText) findViewById(R.id.first_name);
        et_last_name = (EditText) findViewById(R.id.last_name);
        et_blood = (EditText) findViewById(R.id.blood);
        et_dob = (EditText) findViewById(R.id.dob);
        et_drug_allergy = (EditText) findViewById(R.id.drug_allergy);
        et_reg_dose = (EditText) findViewById(R.id.reg_dosing);
        et_hospital = (EditText) findViewById(R.id.hospital);
        et_contact = (EditText) findViewById(R.id.contact);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void save() {
        intialize();
        if (!validate()){
            Toast.makeText(this,"Save has failed", Toast.LENGTH_SHORT).show();
        }
        else {
            toSaveSuccess();
        }
    }

    public void toSaveSuccess() {
    }

    public boolean validate(){
        boolean valid = true;
        if (firstName.isEmpty()||firstName.length()>32){
            et_first_name.setError("Please Enter valid firs tname");
            valid = false;
        }
        if (lastName.isEmpty()||lastName.length()>32){
            et_last_name.setError("Please Enter valid last name");
            valid = false;
        }
        if (bloodType.isEmpty()){
            et_blood.setError("Please Enter valid Blood type");
            valid = false;
        }
        if (dob.isEmpty()){
            et_dob.setError("Please Enter valid Date of birth");
            valid = false;
        }
        if (drugAllergy.isEmpty()){
            et_drug_allergy.setError("Please Enter valid Drug Allergy");
            valid = false;
        }
        if (regDose.isEmpty()){
            et_reg_dose.setError("Please Enter valid Regular dosing");
            valid = false;
        }
        if (hospital.isEmpty()){
            et_hospital.setError("Please Enter valid Hospital");
            valid = false;
        }
        if (contact.isEmpty()){
            et_contact.setError("Please Enter valid Contact");
            valid = false;
        }
        return valid;
    }

    public void intialize() {
        firstName = et_first_name.getText().toString().trim();
        lastName = et_last_name.getText().toString().trim();
        bloodType = et_blood.getText().toString().trim();
        dob = et_dob.getText().toString().trim();
        drugAllergy = et_drug_allergy.getText().toString().trim();
        regDose = et_reg_dose.getText().toString().trim();
        hospital = et_hospital.getText().toString().trim();
        contact = et_contact.getText().toString().trim();
    }
}
