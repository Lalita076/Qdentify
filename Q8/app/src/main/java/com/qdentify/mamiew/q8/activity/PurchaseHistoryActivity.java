package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.adapter.LastTreatAdapterFB;
import com.qdentify.mamiew.q8.adapter.PurchaseHistoryAdapterFB;
import com.qdentify.mamiew.q8.dao.LastTreatModel;
import com.qdentify.mamiew.q8.dao.PatientListModelFB;
import com.qdentify.mamiew.q8.dao.PurchaseObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryActivity extends AppCompatActivity implements PurchaseHistoryAdapterFB.OnItemClickListener {
    private Toolbar toolbar;

    private RecyclerView phRecyclerView;
    private PurchaseHistoryAdapterFB phAadapter;
    private List<PurchaseObject> result;
    private List<String> phKey;

    private Query query;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Purchase History");

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("purchaseQR");

        query = databaseReference.orderByChild("userId").equalTo(userId);

        result = new ArrayList<>();
        phKey = new ArrayList<>();

        phRecyclerView = (RecyclerView) findViewById(R.id.purchase_his);
        phRecyclerView.setHasFixedSize(true);
        phRecyclerView.setLayoutManager(new LinearLayoutManager(PurchaseHistoryActivity.this));

        phAadapter = new PurchaseHistoryAdapterFB(result);
        phRecyclerView.setAdapter(phAadapter);

        bindingData();

        phAadapter.setOnItemClickListener(this);
    }

    private void bindingData() {
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(PurchaseObject.class));
                phKey.add(dataSnapshot.getKey());
                phAadapter.notifyDataSetChanged();

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
                //Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void click() {

    }

    @Override
    public void onItemClick(int position) {
        /*Toast.makeText(this, "You click on card", Toast.LENGTH_SHORT).show();
        Intent info = new Intent(this, LastTreatViewActivity.class);

        info.putExtra("key", ltKey.get(position));
        info.putExtra("date", result.get(position).date);
        info.putExtra("hospital", result.get(position).hospital);
        info.putExtra("doctor", result.get(position).doctorName);
        info.putExtra("symptom", result.get(position).symptom);
        Toast.makeText(this, "Doctor is" + result.get(position).doctorName, Toast.LENGTH_SHORT).show();

        startActivity(info);*/
    }

}
