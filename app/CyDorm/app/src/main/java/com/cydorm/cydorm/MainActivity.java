package com.cydorm.cydorm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.cardList);

        //TODO this may need to change with time
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<ContactInfo> templist = new ArrayList<>();
        templist.add(new ContactInfo());
        templist.add(new ContactInfo());
        templist.add(new ContactInfo());
        templist.add(new ContactInfo());
        mAdapter = new ContactAdapter(templist);

        mRecyclerView.setAdapter(mAdapter);

        Intent i = new Intent(MainActivity.this,
        GroceryManagerActivity.class);
        startActivity(i);
    }
}
