package com.cydorm.cydorm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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

import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/** Activity allowing the adding removal and editing of the grocery list
 * @author Jack Potter, Malcolm Boyd
 **/
public class GroceryManagerActivity extends AppCompatActivity {

    private ListView mGroceryList;
    private EditText mItemEdit;
    private Button mAddButton;
    private Button mRemoveButton;

    private ArrayAdapter<GroceryItem> mAdapter;

    protected int itemInd;
    private static String LOG_TAG = "GroceryMan";
    private StompConnection sc;
    private GroceryListNetwork listNetwork;

    private String selectedPrice;
    private String selectedName;
    private String selectedID;

    private String url = "http://proj309-vc-05.misc.iastate" +
            ".edu:8080/gs-guide-websocket/websocket";

    /** Android create, connects to server pulls and updates grocery list
     *
     * @param Bundle android bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_icon));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroceryManagerActivity.this,
                        AddGroceryActivity.class);
                i.putExtra("price", selectedPrice);
                i.putExtra("name", selectedName);
                i.putExtra("id", selectedID);
                startActivity(i);
            }
        });

        this.listNetwork = new GroceryListNetwork(this);

        mGroceryList = (ListView) findViewById(R.id.grocery_listView);
        mItemEdit = (EditText) findViewById(R.id.item_editText);
        mAddButton = (Button) findViewById(R.id.add_button);
        mRemoveButton = (Button) findViewById(R.id.remove_button);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        mGroceryList.setAdapter(mAdapter);

        this.sc = new StompConnection("8B8CD50EF9319D75C54BB3489A8810D3");
        subscribeAdd();


        this.itemInd = -1;

        //Get grocery list and add it
        this.getGroceryList();

        //Add button click
        mAddButton.setOnClickListener(new AddButtonClickedListener());
        mRemoveButton.setOnClickListener(new RemoveButtonClickedListener());

        // Clicking Item
        mGroceryList.setOnItemClickListener(new GroceryItemClickedListener());
    }

    /** Subscribe to the addition of new grocery items */
    private void subscribeAdd() {

        //Subscribe to new things being added
        this.sc.sc.topic("/allGroceries")
                .subscribe(topicMessage -> {

                    try {
                        JSONArray ja =
                                new JSONArray(Normalizer.normalize(topicMessage.getPayload(), Normalizer.Form.NFC));
                        for(int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            updateList(jo);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        this.dumpGroceryAndUpdate(this.sc.sc);
    }

    /** Upadate grocery list with new item
     * @param JSONObject Json to parse new item from
     */
    private void updateList(JSONObject jo) {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mAdapter.add(new GroceryItem(jo));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mGroceryList.setAdapter(mAdapter);
            }
        });
    }

    /** Dumps all groceries and updates the list with them
     *
     * @param StompClient The stomp client used in network calls
     */
    private void dumpGroceryAndUpdate(StompClient mStompClient) {
        this.sc.sc.send("/dumpGrocery", "{ \"netID\":\"mjboyd\" }").subscribe(topicMessage -> {
            try {
                JSONArray ja =
                        new JSONArray(Normalizer.normalize(topicMessage.toString(),
                                Normalizer.Form.NFC));
                for(int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    updateList(jo);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Delete a grocery item and update list
     *
     * @param StompClient Stomp client for network calls
     * @param String Id of the item to delete
     */
    private void removeGroceryAndUpdate(StompClient mStompClient, String id) {
        this.sc.sc.send("/deleteGroceryItem", "{\"netID\":\"mjboyd\" , " +
                "\"grocery_id\" : \"" + id + "\" }").subscribe();

        dumpGroceryAndUpdate(mStompClient);
        this.mAdapter.notifyDataSetChanged();
    }

    /** Add a grocery item and update list
     *
     * @param StompClient Stomp client for network calls
     * @param GroceryItem The item to add
     */
    private void addAndUpdate(StompClient mStompClient, GroceryItem i) {
        String is = String.format("{ \"groceryItem\": \"%s\", " +
                "\"groceryPrice\" : \"%s\"," +
                "\"approved\":\"F\", \"studentID\":\"mjboyd\"}", i.getItem(),
                i.getPrice());

        this.sc.sc.send("/addGroceryItem",
                is).subscribe();

        System.out.print("Just added one");
        this.mAdapter.notifyDataSetChanged();
    }



    /**
     * Assigns a new list and adapter, deleting list.
     */
    private List<GroceryItem> getGroceryList() {
        final ArrayList<GroceryItem> list = new ArrayList<>();
        this.mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mGroceryList.setAdapter(mAdapter);
        return list;
    }

    /**
     * Class handing the removal. Calls remove and updates the adapter.
     */
    private class RemoveButtonClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(itemInd >= 0) {
                //listNetwork.removeListItem(mAdapter.getItem(itemInd));
                removeGroceryAndUpdate(sc.sc,
                        mAdapter.getItem(itemInd).getID());
            }
        }
    }

    /**
     * Class handling adding the item and updating the lists
     * Also handles the logic for editing the item.
     */
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
                    removeGroceryAndUpdate(sc.sc, newItem.getID());
                    addAndUpdate(sc.sc, newItem);

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
                    addAndUpdate(sc.sc, gi);


                    mAdapter.notifyDataSetChanged();
                    mItemEdit.setText("");
                }
            }
    }

    /**
     * The listener that sets up editing for a grocery item when clicked.
     */
    private class GroceryItemClickedListener implements AdapterView.OnItemClickListener {
             @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemInd = i;
                mItemEdit.setText(adapterView.getItemAtPosition(i).toString());
            }
    }

}
