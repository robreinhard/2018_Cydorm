package com.cydorm.cydorm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.List;

public class GroceryManagerActivity extends AppCompatActivity {

    private ListView mGroceryList;
    private EditText mItemEdit;
    private Button mAddButton;
    private GroceryListNetwork listNetwork;

    private ArrayAdapter<GroceryItem> mAdapter;
    protected int itemInd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        mGroceryList = (ListView) findViewById(R.id.grocery_listView);
        mItemEdit = (EditText) findViewById(R.id.item_editText);
        mAddButton = (Button) findViewById(R.id.add_button);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mGroceryList.setAdapter(mAdapter);
        this.listNetwork = new GroceryListNetwork();


        this.itemInd = -1;

        List<GroceryItem> gList = this.listNetwork.getGroceryList();
        for(GroceryItem i : gList) {
            mAdapter.add(i);
        }

        //Add button click
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item;
                if(itemInd >= 0) {
                    item = mItemEdit.getText().toString();

                    mAdapter.getItem(itemInd).setItem(item);

                    //HERE insert update code
                    listNetwork.updateListItem(mAdapter.getItem(itemInd));

                    itemInd = -1;
                    mAdapter.notifyDataSetChanged();
                    mItemEdit.setText("");

                } else {
                    item = mItemEdit.getText().toString();
                    GroceryItem gi = new GroceryItem(item);
                    mAdapter.add(gi);

                    // HERE INSERT UPDATE CODE
                    listNetwork.addListItem(gi);

                    mAdapter.notifyDataSetChanged();
                    mItemEdit.setText("");
                }

            }
        });

        // Clicking Item
        mGroceryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemInd = i;
                mItemEdit.setText(adapterView.getItemAtPosition(i).toString());
            }
        });
    }
}
