package com.qdentify.mamiew.q8.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.qdentify.mamiew.q8.activity.PurchaseHistoryActivity;
import com.qdentify.mamiew.q8.dao.UserDataModel;

public class TabThreeAccount extends Fragment implements View.OnClickListener {
    private TextView userName, email, address;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_user;

    private FirebaseAuth firebaseAuth;
    private String userId;

    private CardView historyCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_three_account, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();


        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();


        databaseReference_user = firebaseDatabase.getReference().child("users").child(userId);
        initInstances(rootView);


        bind();
        return rootView;
    }

    private void bind() {
        databaseReference_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email_data = (String) dataSnapshot.child("userEmail").getValue();
                //String address_data = (String) dataSnapshot.child("userAddress").getValue();

                email.setText(email_data);
                //address.setText(address_data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initInstances(View rootView) {

        email = (TextView) rootView.findViewById(R.id.tv_email);
        //address = (TextView) rootView.findViewById(R.id.tv_address);

        historyCard = (CardView)rootView.findViewById(R.id.history_card);

        historyCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent goHistory = new Intent(getActivity(), PurchaseHistoryActivity.class);
        startActivity(goHistory);
    }
}
