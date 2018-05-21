package com.qdentify.mamiew.q8.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.activity.PersonInfoActivity;
import com.qdentify.mamiew.q8.adapter.PatientListAdapterFB;
import com.qdentify.mamiew.q8.dao.PatientListModelFB;

import java.util.ArrayList;
import java.util.List;

public class TabOnePatientsFB extends Fragment implements PatientListAdapterFB.OnItemClickListener{

    private RecyclerView pRecyclerView;
    private PatientListAdapterFB pAadapter;
    private ProgressDialog progressDialog;

    private List<PatientListModelFB> result;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_one_patients,container,false);

        progressDialog = new ProgressDialog(rootView.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("patient/data");

        result = new ArrayList<>();

        pRecyclerView = (RecyclerView) rootView.findViewById(R.id.patient_view);
        pRecyclerView.setHasFixedSize(true);
        pRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        pAadapter = new PatientListAdapterFB(result);
        pRecyclerView.setAdapter(pAadapter);



        bindingData();

        pAadapter.setOnItemClickListener(TabOnePatientsFB.this);

        return rootView;
    }

    private void bindingData() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(PatientListModelFB.class));
                pAadapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"No data",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "You click on card", Toast.LENGTH_LONG).show();
        Intent info = new Intent(getActivity(), PersonInfoActivity.class);
        Log.d("TabOne","pParcelable going");
        PatientListModelFB paParcelable = new PatientListModelFB();
        info.putExtra("key", result.get(position).key);
        info.putExtra("name", result.get(position).firstName+" "+result.get(position).lastName);
        info.putExtra("dob", result.get(position).dob);
        info.putExtra("bloodType", result.get(position).bloodType);
        info.putExtra("contact", result.get(position).contact);
        info.putExtra("disease", result.get(position).disease);
        info.putExtra("regDosing", result.get(position).regDosing);
        info.putExtra("drugAllergy", result.get(position).drugAllergy);
        info.putExtra("hospital", result.get(position).hospitalName);
        info.putExtra("thumbnail", result.get(position).thumbnail);

        startActivity(info);
    }

    private int getIemIndex(PatientListModelFB dataModel){
        int index = -1;
        for(int i =0; i < result.size(); i++){
            if(result.get(i).key.equals(dataModel.key)){
                index = i;
                break;
            }
        }
        return  index;
    }

/*
    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "You click on card", Toast.LENGTH_LONG).show();
        Intent info = new Intent(getActivity(),PersonInfoActivity.class);
        Log.d("TabOne","pParcelable going");
        patientListDaoParcelable = new PatientListDao();
        patientListDaoParcelable.setpID(patientListColletionDao.getData().get(position).getpID());
        patientListDaoParcelable.setFirstName(patientListColletionDao.getData().get(position).getFirstName());
        patientListDaoParcelable.setLastName(patientListColletionDao.getData().get(position).getLastName());
        patientListDaoParcelable.setDob(patientListColletionDao.getData().get(position).getDob());
        patientListDaoParcelable.setBloodType(patientListColletionDao.getData().get(position).getBloodType());
        patientListDaoParcelable.setDisease(patientListColletionDao.getData().get(position).getDisease());
        patientListDaoParcelable.setRegDosing(patientListColletionDao.getData().get(position).getRegDosing());
        patientListDaoParcelable.setDrugAllergies(patientListColletionDao.getData().get(position).getDrugAllergies());
        patientListDaoParcelable.setHospital(patientListColletionDao.getData().get(position).getHospital());
        patientListDaoParcelable.setContact(patientListColletionDao.getData().get(position).getContact());
        info.putExtra("pParcelable", patientListDaoParcelable);
        Log.d("TabOne","pParcelable success");
        startActivity(info);
    }*/

}
