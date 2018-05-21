package com.qdentify.mamiew.q8.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.qdentify.mamiew.q8.R;

public class LastTreatActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_treat);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Treat History");
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
        Toast.makeText(this,"add history ",Toast.LENGTH_SHORT).show();
        Intent add = new Intent(LastTreatActivity.this, AddLastTreateActivity.class);
        startActivity(add);
    }
}
