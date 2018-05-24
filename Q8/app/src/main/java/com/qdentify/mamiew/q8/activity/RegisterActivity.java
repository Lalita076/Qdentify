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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.dao.UserDataModel;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister, btnToSignIn;
    EditText etEmail, etPassword;
    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    DatabaseReference firebaseDatabase;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

           /* String user_id = firebaseAuth.getCurrentUser().getUid();
            Toast.makeText(RegisterActivity.this,"UID :"+user_id,Toast.LENGTH_LONG).show();
            DatabaseReference user = firebaseDatabase.child(user_id);*/
        }
        initInstances();
        btnRegister.setOnClickListener(this);
        btnToSignIn.setOnClickListener(this);

    }

    private void initInstances() {

	
	
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnToSignIn = (Button) findViewById(R.id.btn_signin);

        etEmail = (EditText) findViewById(R.id.et_mail);
        etPassword = (EditText) findViewById(R.id.et_password);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users");

    }

    @Override
    public void onClick(View view) {
        if (view==btnRegister){
            register();
        }
        else if(view==btnToSignIn){
            //login();
            Intent singin = new Intent(getApplicationContext(),LoginActivity.class);
            Toast.makeText(RegisterActivity.this, "go to Login",Toast.LENGTH_SHORT).show();
            startActivity(singin);

           // Toast.makeText(RegisterActivity.this, "Caregiver id :",Toast.LENGTH_SHORT).show();
        }

        /*Intent login = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(login);*/

    }

    private void register() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //emial is empty
            Toast.makeText(this, "Please enter mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering please wait..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            String user_id2 = firebaseAuth.getCurrentUser().getUid();
                            firebaseDatabase.child(user_id2).child("userEmail").setValue(email);
                            firebaseDatabase.child(user_id2).child("userPassword").setValue(password);
                            finish();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
							
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Could not register.. please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
