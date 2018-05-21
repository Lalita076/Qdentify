package com.qdentify.mamiew.q6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContentPatientsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabone_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MainAdapter();
        adapter.setItemList(createItem());
        recyclerView.setAdapter(adapter);
    }

    private List<BaseItem> createItem() {
        List<BaseItem> itemList = new ArrayList<>();
        itemList.add(new CardViewItem()
                .setImage(R.drawable.b_boy)
                .setText("Christopher Hudson"));
        itemList.add(new CardViewItem()
                .setImage(R.drawable.b_girl)
                .setText("Maria Hudson"));
        return itemList;
    }
}
