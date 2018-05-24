package com.qdentify.mamiew.q8.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.adapter.PatientListAdapterFB;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class PatientEditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirstName, etLastName, etDob, etContact, etDisease, etDrugAllergy, etRegDosing, etHospitalName;
    private TextView datePick;
    private Button choosePic;
    ImageView imThumbnail;
    private Spinner blood;
    private ImageView imageView;
    private Calendar mCurrrntDate;
    private int day, month, year;
    public String key, firstName, lastName, bloodType, dob, disease, drugAllergy, regDosing, hospital, contact, thumbnail;
    private Uri downloadUrl;

    private String _caregiverId;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private DatabaseReference firebaseDatabase;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Patient Profile");

        Intent _Uid = getIntent();
        _caregiverId = _Uid.getStringExtra("userId");

        Intent intent = getIntent();
        key = intent.getStringExtra("patientId");
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        dob = intent.getStringExtra("dob");
        bloodType = intent.getStringExtra("bloodType");
        contact = intent.getStringExtra("contact");
        disease = intent.getStringExtra("disease");
        regDosing = intent.getStringExtra("regDosing");
        drugAllergy = intent.getStringExtra("drugAllergy");
        hospital = intent.getStringExtra("hospital");
        thumbnail = intent.getStringExtra("thumbnail");


        initInstances();

        bindingData();
    }

    private void bindingData() {
        etFirstName.setText(firstName + " " + lastName);
        etLastName.setText(dob);
        //blood.setText(bloodType);
        etContact.setText(contact);
        etDisease.setText(disease);
        etRegDosing.setText(regDosing);
        etDrugAllergy.setText(drugAllergy);
        etHospitalName.setText(hospital);
        Picasso.with(PatientEditActivity.this).load(thumbnail).into(imThumbnail);

    }

    private void initInstances() {
        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        datePick = (TextView) findViewById(R.id.tv_dob);
        etContact = (EditText) findViewById(R.id.et_contact);
        blood = (Spinner) findViewById(R.id.et_blood);
        etDisease = (EditText) findViewById(R.id.et_disease);
        etRegDosing = (EditText) findViewById(R.id.et_reg_dosing);
        etDrugAllergy = (EditText) findViewById(R.id.et_drug_allergy);
        etHospitalName = (EditText) findViewById(R.id.et_hospital);
        imThumbnail = (ImageView)findViewById(R.id.im_thumbnail);

        choosePic = (Button) findViewById(R.id.btn_choose);
        imageView = (ImageView) findViewById(R.id.image_view);

        mCurrrntDate = Calendar.getInstance();

        day = mCurrrntDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrrntDate.get(Calendar.MONTH);
        year = mCurrrntDate.get(Calendar.YEAR);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("patient").child("data");

        datePick.setOnClickListener(this);
        choosePic.setOnClickListener(this);

    }

    private void save() {
        String patientName = etFirstName.getText().toString().trim();
        String patientSurname = etLastName.getText().toString().trim();
        String patientBirthday = datePick.getText().toString().trim();
        String patientContact = etContact.getText().toString().trim();
        String patientBlood = blood.getSelectedItem().toString().trim();
        String patientDisease = etDisease.getText().toString().trim();
        String patientRegDosing = etRegDosing.getText().toString().trim();
        String patientDrugAllergy = etDrugAllergy.getText().toString().trim();
        String patientHospital = etHospitalName.getText().toString().trim();
        String patientThumbnail = downloadUrl.toString();

        String newPatient = firebaseDatabase.push().getKey();
        Toast.makeText(this, "Patient key :" + newPatient, Toast.LENGTH_SHORT).show();

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", patientName);
        map.put("lastName", patientSurname);
        map.put("dob", patientBirthday);
        map.put("contact", patientContact);
        map.put("bloodType", patientBlood);
        map.put("disease", patientDisease);
        map.put("regDosing", patientRegDosing);
        map.put("drugAllergy", patientDrugAllergy);
        map.put("hospitalName", patientHospital);
        map.put("thumbnail", patientThumbnail);
        map.put("status", "inactive");
        map.put("caregiverId", _caregiverId);

        firebaseDatabase.child(newPatient).setValue(map);
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            uploadFile();
            Toast.makeText(this, "Add patient success", Toast.LENGTH_SHORT).show();
            Intent gohome = new Intent(PatientEditActivity.this, MainActivity.class);
            startActivity(gohome);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == datePick) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(PatientEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    datePick.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }, year, month, day);
            datePickerDialog.show();
        }
        if (v == choosePic) {
            openFileChooser();
        }
    }

    public void uploadFile() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("profile-pic/"/*+ UUID.randomUUID().toString()*/).child(filePath.getLastPathSegment());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            Toast.makeText(PatientEditActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            downloadUrl = taskSnapshot.getDownloadUrl();
                            save();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(PatientEditActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Upload" + (int) progress + "%");
                        }
                    });

        }
    }

}