package com.qdentify.mamiew.q8.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.qdentify.mamiew.q8.R;
//import com.qdentify.mamiew.q8.Upload;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.widget.TextView;
import android.widget.Toast;

//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class AddPatientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstName, lastName, dob, contact, disease, drugAllergy, regDosing, hospitalName;
    private TextView datePick, hospital;
    private Button choosePic, uploadPic;
    private Spinner blood;
    private ImageView imageView;
    private Calendar mCurrrntDate;
    private int day, month, year;


    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private DatabaseReference firebaseDatabase;
    private StorageReference firebaseStorage;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add patient Profile");

        initInstances();

        //save();

        choosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        uploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    private void initInstances() {
        firstName = (EditText) findViewById(R.id.et_first_name);
        lastName = (EditText) findViewById(R.id.et_last_name);
        datePick = (TextView) findViewById(R.id.tv_dob);
        contact = (EditText) findViewById(R.id.et_contact);
        blood = (Spinner) findViewById(R.id.et_blood);
        disease = (EditText) findViewById(R.id.et_disease);
        regDosing = (EditText) findViewById(R.id.et_reg_dosing);
        drugAllergy = (EditText) findViewById(R.id.et_drug_allergy);
        hospitalName = (EditText) findViewById(R.id.et_hospital);

        choosePic = (Button) findViewById(R.id.btn_choose);
        uploadPic = (Button) findViewById(R.id.btn_upload);
        imageView = (ImageView) findViewById(R.id.image_view);

        mCurrrntDate = Calendar.getInstance();

        day = mCurrrntDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrrntDate.get(Calendar.MONTH);
        year = mCurrrntDate.get(Calendar.YEAR);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // firebaseStorage = FirebaseStorage.getInstance().getReference("profile-pic");
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("patient").child("data");
        //firebaseDatabase_Hospital = FirebaseDatabase.getInstance().getReference().child("Hospital");

        datePick.setOnClickListener(this);
    }

    private void save() {
        String patientName = firstName.getText().toString().trim();
        String patientSurname = lastName.getText().toString().trim();
        String patientBirthday = datePick.getText().toString().trim();
        String patientContact = contact.getText().toString().trim();
        String patientBlood = blood.getSelectedItem().toString().trim();
        String patientDisease = disease.getText().toString().trim();
        String patientRegDosing = regDosing.getText().toString().trim();
        String patientDrugAllergy = drugAllergy.getText().toString().trim();
        String patientHospital = hospitalName.getText().toString().trim();
        //String patientPic =  downloadUrl;/*filePath.getPath().toString().trim();*/


        String newPatient = firebaseDatabase.push().getKey();

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
        //map.put("thumbnail", patientPic);
        // map.put("hospital",patientHospital);

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
            // Log.d("Add Patient", String.valueOf(filePath));
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
            //save();
            Toast.makeText(this, "Add patient success", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == datePick) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddPatientActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    datePick.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }, year, month, day);
            datePickerDialog.show();
        }
    }

    /*private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }*/
    public void uploadFile()
    {
        if (filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("profile-pic/"+ UUID.randomUUID().toString()).child(filePath.getLastPathSegment());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            firebaseDatabase.child("patient").child("data").child("thumbnail").setValue(downloadUrl.toString());

                            progressDialog.dismiss();
                            Toast.makeText(AddPatientActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            save();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(AddPatientActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Upload" + (int) progress + "%");
                        }
                    });

        }
    }


}