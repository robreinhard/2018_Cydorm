package com.cydorm.cydorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.volley.Response;
import android.os.SystemClock;
import com.techdew.stomplibrary.Stomp;
import com.techdew.stomplibrary.StompClient;
import org.java_websocket.WebSocket;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GroceryManagerActivity extends AppCompatActivity {

    private ListView mGroceryList;
    private EditText mItemEdit;
    private Button mAddButton;
    private Button mRefreshButton;
    private Button mRemoveButton;

    private ArrayAdapter<GroceryItem> mAdapter;

    protected int itemInd;
    private static String LOG_TAG = "GroceryMan";
    private StompClient mStompClient;
    private GroceryListNetwork listNetwork;

    private String url = "http://proj309-vc-05.misc.iastate" +
            ".edu:8080/gs-guide-websocket/websocket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        this.listNetwork = new GroceryListNetwork(this);

        mGroceryList = (ListView) findViewById(R.id.grocery_listView);
        mItemEdit = (EditText) findViewById(R.id.item_editText);
        mAddButton = (Button) findViewById(R.id.add_button);
        mRemoveButton = (Button) findViewById(R.id.remove_button);
        mRefreshButton= (Button) findViewById(R.id.refresh_button);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        mGroceryList.setAdapter(mAdapter);

        initStomp();


        this.itemInd = -1;

        //Get grocery list and add it
        this.getGroceryList();

        //Add button click
        mAddButton.setOnClickListener(new AddButtonClickedListener());
        mRefreshButton.setOnClickListener(new RefreshButtonClickedListener());
        mRemoveButton.setOnClickListener(new RemoveButtonClickedListener());

        // Clicking Item
        mGroceryList.setOnItemClickListener(new GroceryItemClickedListener());
    }

    private void initStomp() {

        //Stomp & websocket setup
        Map<String, String> headers = Collections.singletonMap("Cookie",
                "JSESSIONID=AE3A98C16C25FF2B24D6932B37476617");

        //Authenticate via post request

        this.mStompClient = Stomp.over(WebSocket.class, url, headers);
        this.mStompClient.topic("/allGroceries")
                .subscribe(topicMessage -> {

                    try {
                        JSONArray ja =
                                new JSONArray(Normalizer.normalize(topicMessage.getPayload(), Normalizer.Form.NFC));
                        for(int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        mAdapter.add(new GroceryItem(jo.get(
                                                "groceryItem").toString(),
                                                jo.get("id").toString(),
                                                "",
                                                "",
                                                jo.get("groceryPrice").toString()));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    mGroceryList.setAdapter(mAdapter);
                                }
                            });

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        this.mStompClient.connect();

        this.mStompClient.lifecycle()
                .subscribe(lifecycleEvent -> {
                    switch (lifecycleEvent.getType()) {
                        case OPENED:
                            Log.d("STOMP","Stomp connection opened");
                            break;
                        case ERROR:
                            Log.d("STOMP","Stomp connection error");
                            break;
                        case CLOSED:
                            Log.d("STOMP","Stomp connection closed");
                    }
                });
    }

    private void auth() {
        System.out.println("About to do auth");

    }

    private List<GroceryItem> getGroceryList() {

        final ArrayList<GroceryItem> list = new ArrayList<>();
        this.mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mGroceryList.setAdapter(mAdapter);
        return list;
    }

    private class RemoveButtonClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(itemInd >= 0) {
                //listNetwork.removeListItem(mAdapter.getItem(itemInd));
            }
        }
    }

    private class RefreshButtonClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            getGroceryList();
        }
    }

    private class AddButtonClickedListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                String item;
                if(itemInd >= 0) {
                    item = mItemEdit.getText().toString();

                    mAdapter.getItem(itemInd).setItem(item);

                    GroceryItem newItem =
                            new GroceryItem(mAdapter.getItem(itemInd));
                    newItem.setItem(item);

                    //HERE insert update code
                    //listNetwork.updateListItem(mAdapter.getItem(itemInd),
                     //       newItem);
                    SystemClock.sleep(2000);
                    getGroceryList();

                    itemInd = -1;
                    mAdapter.notifyDataSetChanged();
                    mItemEdit.setText("");

                } else {
                    item = mItemEdit.getText().toString();
                    GroceryItem gi = new GroceryItem(item, "0", "Malcolm",
                            "Boyd", "7.99");
                    mAdapter.add(gi);

                    // HERE INSERT UPDATE CODE
                    //listNetwork.addListItem(gi);
                    getGroceryList();

                    mAdapter.notifyDataSetChanged();
                    mItemEdit.setText("");
                }
            }
    }

    private class GroceryItemClickedListener implements AdapterView.OnItemClickListener {
             @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemInd = i;
                mItemEdit.setText(adapterView.getItemAtPosition(i).toString());
            }
    }

}
