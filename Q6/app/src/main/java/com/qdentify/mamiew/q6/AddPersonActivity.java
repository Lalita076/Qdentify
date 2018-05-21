package com.qdentify.mamiew.q6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddPersonActivity extends AppCompatActivity {
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        toolbar = findViewById(R.id.bar_view_patients);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("View Patients");

        Button buttonnj = (Button) findViewById (R.id.buttonnj);

        buttonnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("my Tag","This is my tag");
            }
        });


    }
}
