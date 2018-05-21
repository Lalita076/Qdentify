package com.qdentify.mamiew.q8.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.activity.EditCareGiverActivity;
import com.qdentify.mamiew.q8.activity.LoginActivity;
import com.qdentify.mamiew.q8.dao.UserDataModel;

public class TabThreeAccount extends Fragment implements View.OnClickListener {
    private Button btnLogout;
    private TextView userName, email, address;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_user;

    private FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_three_account,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference_user = firebaseDatabase.getReference().child("user/user-data/user1");

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(getContext(), LoginActivity.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        initInstances(rootView);

        //bindingData();
        bind();
        return rootView;
    }

    private void bind() {
        databaseReference_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName_data = (String) dataSnapshot.child("userName").getValue();
                String email_data = (String) dataSnapshot.child("email").getValue();
                String address_data = (String) dataSnapshot.child("address").getValue();
                userName.setText(userName_data);
                email.setText(email_data);
                address.setText(address_data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*
        private void bindingData() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserDataModel value = dataSnapshot.getValue(UserDataModel.class);
                    userName.setText(value.userName);
                    email.setText(value.email);
                    address.setText(value.address);
                    Log.d("TabThree", "Value is "+value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    */
    private void initInstances(View rootView) {

        email = (TextView)rootView.findViewById(R.id.tv_email);
        address = (TextView)rootView.findViewById(R.id.tv_address);

        btnLogout = (Button)rootView.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnLogout){

        }

    }
}
