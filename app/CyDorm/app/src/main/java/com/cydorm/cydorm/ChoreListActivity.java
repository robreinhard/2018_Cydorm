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

/**
 * Activity displaying the chore list
 *
 * @author Jack Potter
 */
public class ChoreListActivity extends AppCompatActivity {

    private StompConnection sc;
    ListView mChoreList;
    private ArrayAdapter<ChoreItem> mAdapter;

    /** Android create the activity
     *
     * Creates the activity, adds the plus button at bottom right, subscribes
     * to server, and updates the chore list.
     *
     * @param Bundle android context
     */
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


        this.sc = new StompConnection("8B8CD50EF9319D75C54BB3489A8810D3");


        //Subscribe to update the list
        subscribeToChores();

    }

    /**Subscribes to the chores web socket adds list items to chore list
     */
    private void subscribeToChores() {
        System.out.println("XXXXXXXXX Just got ");
        this.sc.sc.send("/dumpChore", "{ \"netID\" : \"mjboyd\" }").subscribe(topicMessage -> {

            System.out.println("XXXXXXXXX Just got " + topicMessage.toString());
            try {
                //JSONArray ja = new JSONArray(topicMessage.getPayload());
                mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                System.out.println("Just got " + topicMessage.toString());
                updateList(new JSONObject(topicMessage.toString()));
                //for(int i = 0; i < ja.length(); i++) {
                    //updateList(ja.getJSONObject(i));
                //}
                mChoreList.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /** Update the chore list with new chore
     * @param JSONObject Json to be parsed and added as a new chore
     */
    private void updateList(JSONObject jo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mAdapter.add(new ChoreItem(jo));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
