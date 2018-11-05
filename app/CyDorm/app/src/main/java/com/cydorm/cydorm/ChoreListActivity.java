package com.cydorm.cydorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChoreListActivity extends AppCompatActivity {

    private StompConnection sc;
    ListView mChoreList;
    private ArrayAdapter<ChoreItem> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_icon));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChoreListActivity.this,
                        AddChoreActivity.class);
                startActivity(i);
            }
        });

        mChoreList = (ListView) findViewById(R.id.chore_listView);
        mAdapter = new ArrayAdapter<ChoreItem>(this,
                android.R.layout.simple_list_item_1);

        mChoreList.setAdapter(mAdapter);


        this.sc = new StompConnection("03D4FBCBB169220A8B7380794A544621");

        //Subscribe to update the list
        subscribeToChores();

    }

    private void subscribeToChores() {
        this.sc.sc.topic("/dumpChore").subscribe(topicMessage -> {

            try {
                JSONArray ja = new JSONArray(topicMessage.getPayload());
                for(int i = 0; i < ja.length(); i++) {
                    updateList(ja.getJSONObject(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void updateList(JSONObject jo) {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mAdapter.add(new ChoreItem(jo));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mChoreList.setAdapter(mAdapter);
            }
        });
    }

}
