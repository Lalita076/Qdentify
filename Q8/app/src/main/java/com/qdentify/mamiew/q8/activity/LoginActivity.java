package com.qdentify.mamiew.q8.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.UserDataModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnToRegister, btnSignIn;
    EditText etPassword,etEmail;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        initInstances();

    }

    private void initInstances() {

        btnToRegister = (Button)findViewById(R.id.btn_register);
        btnSignIn = (Button)findViewById(R.id.btn_signin);

        etEmail = (EditText)findViewById(R.id.et_mail);
        etPassword = (EditText)findViewById(R.id.et_password);

        btnToRegister.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btnSignIn){
            signIn();
        }
        if(view==btnToRegister){
            startActivity(new Intent(this,RegisterActivity.class));

        }

    }

    private void signIn() {
       String email = etEmail.getText().toString().trim();
       String password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //emial is empty
            Toast.makeText(this, "Please enter mail", Toast.LENGTH_SHORT).show();;
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();

        }
        progressDialog.setMessage("Register User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });
    }
}
