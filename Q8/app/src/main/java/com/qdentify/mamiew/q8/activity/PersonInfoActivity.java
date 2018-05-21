package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.PatientInfo;
import com.qdentify.mamiew.q8.dao.PatientListColletionDao;
import com.qdentify.mamiew.q8.dao.PatientListDao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PersonInfoActivity extends AppCompatActivity implements  CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    Toolbar toolbar;
    TextView tvHeadName, tvBloodType, tvDob, tvDisease, tvDrugAllergy, tvRegDose, tvHospital, tvContact;
    ImageView imThumbnail;
    Switch swActiveQR;
    String key, headName, bloodType, dob, disease, drugAllergy, regDosing, hospital, contact, thumbnail;
    CardView lastTreatCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Patient Profile");

        initInstances();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        headName = intent.getStringExtra("name");
        dob = intent.getStringExtra("dob");
        bloodType = intent.getStringExtra("bloodType");
        contact = intent.getStringExtra("contact");
        disease = intent.getStringExtra("disease");
        regDosing = intent.getStringExtra("regDosing");
        drugAllergy = intent.getStringExtra("drugAllergy");
        hospital = intent.getStringExtra("hospital");
        thumbnail = intent.getStringExtra("thumbnail");


        bindingData();

    }

    private void bindingData() {
        tvHeadName.setText(headName);
        tvDob.setText(dob);
        tvBloodType.setText(bloodType);
        tvContact.setText(contact);
        tvDisease.setText(disease);
        tvRegDose.setText(regDosing);
        tvDrugAllergy.setText(drugAllergy);
        tvHospital.setText(hospital);
        Picasso.with(PersonInfoActivity.this).load(thumbnail).into(imThumbnail);

    }

    private void initInstances() {
        swActiveQR = (Switch)findViewById(R.id.sw_active);

        tvHeadName = (TextView)findViewById(R.id.tv_head_name);
        tvBloodType =  (TextView)findViewById(R.id.tv_blood);
        tvDob =  (TextView)findViewById(R.id.tv_dob);
        tvDisease =  (TextView)findViewById(R.id.tv_disease);
        tvDrugAllergy =  (TextView)findViewById(R.id.tv_drug_allergy);
        tvRegDose = (TextView)findViewById(R.id.tv_reg_dosing);
        tvHospital = (TextView)findViewById(R.id.tv_hospital);
        tvContact = (TextView)findViewById(R.id.tv_contact);
        imThumbnail = (ImageView)findViewById(R.id.im_thumbnail);

        lastTreatCard = (CardView)findViewById(R.id.card_lasttreat);

        swActiveQR.setOnCheckedChangeListener(this);
        lastTreatCard.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /**if (id == R.id.action_edit) {
            return true;
        }
        else if (id == R.id.action_delete) {
            return true;
        }**/
        switch (id){
            case R.id.action_edit:
                Toast.makeText(this,"press edit",Toast.LENGTH_SHORT).show();
                Intent editPatient = new Intent(PersonInfoActivity.this,PatientEditActivity.class);
                //Toast.makeText(this, "Click Edit",Toast.LENGTH_SHORT).show();
                startActivity(editPatient);
                break;
            case R.id.action_delete:
                Toast.makeText(this,"press delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_purchase_qr:
                //Toast.makeText(this,"press purchase QR",Toast.LENGTH_SHORT).show();
                Intent purchaseIntent = new Intent(PersonInfoActivity.this,PurchaseQRActivity.class);
               // purchaseIntent.putExtra("pID",patientListDaoParcelable.getpID());
                //purchaseIntent.putExtra("name",patientListDaoParcelable.getFirstName()+" "+patientListDaoParcelable.getLastName());
                Toast.makeText(this, "Bundle OK",Toast.LENGTH_SHORT).show();
                startActivity(purchaseIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Toast.makeText(this,"QR is Active",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "QR is Inactive",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent lastTreat = new Intent(PersonInfoActivity.this,LastTreatActivity.class);
        startActivity(lastTreat);
    }
}
