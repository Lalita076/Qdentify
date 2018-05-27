package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.qdentify.mamiew.q8.R;
import com.qdentify.mamiew.q8.adapter.LastTreatAdapterFB;
import com.qdentify.mamiew.q8.dao.LastTreatModel;
import com.qdentify.mamiew.q8.dao.PatientListModelFB;

import java.util.ArrayList;
import java.util.List;

public class LastTreatActivity extends AppCompatActivity implements LastTreatAdapterFB.OnItemClickListener {
    private Toolbar toolbar;
    private String patientId;
    private RecyclerView ltRecyclerView;
    private LastTreatAdapterFB ltAadapter;
    private List<LastTreatModel> result;
    private List<String> ltKey;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_treat);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Treat History");

        Intent intent = getIntent();
        patientId = intent.getStringExtra("patientId");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("patient/data").child(patientId).child("lastTreat");

        result = new ArrayList<>();
        ltKey = new ArrayList<>();

        ltRecyclerView = (RecyclerView) findViewById(R.id.last_treat_view);
        ltRecyclerView.setHasFixedSize(true);
        ltRecyclerView.setLayoutManager(new LinearLayoutManager(LastTreatActivity.this));

        ltAadapter = new LastTreatAdapterFB(result);
        ltRecyclerView.setAdapter(ltAadapter);

        bindingData();

        ltAadapter.setOnItemClickListener(LastTreatActivity.this);
    }

    private void bindingData() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(LastTreatModel.class));
                //.makeText(LastTreatActivity.this,"Doctor " +dataSnapshot.child("doctorName").getValue(),Toast.LENGTH_SHORT).show();
                ltAadapter.notifyDataSetChanged();
                ltKey.add(dataSnapshot.getKey());

                //progressDialog.dismiss();
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
                Toast.makeText(LastTreatActivity.this,"No data",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_last_treat) {
            click();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void click() {
        //Toast.makeText(this,"add history ",Toast.LENGTH_SHORT).show();
        Intent add = new Intent(LastTreatActivity.this, LastTreatAddActivity.class);
        add.putExtra("patientId", patientId);
        startActivity(add);
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "You click on card", Toast.LENGTH_SHORT).show();
        Intent info = new Intent(LastTreatActivity.this, LastTreatViewActivity.class);

        info.putExtra("key", ltKey.get(position));
        info.putExtra("date", result.get(position).date);
        info.putExtra("hospital", result.get(position).hospital);
        info.putExtra("doctor", result.get(position).doctorName);
        info.putExtra("symptom", result.get(position).symptom);
        //Toast.makeText(this, "Doctor is" +result.get(position).doctorName, Toast.LENGTH_SHORT).show();

        startActivity(info);
    }

}
