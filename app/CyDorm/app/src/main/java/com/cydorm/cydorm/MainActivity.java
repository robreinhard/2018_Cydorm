package com.cydorm.cydorm;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup the IconMappingScheme
        final IconMapping im = new IconMapping();


        //If you want to add a new activity to the home screen, create the
        // activity and add it to the below lines
        im.addMapping(GroceryManagerActivity.class, R.drawable.ic_launcher_foreground);
        im.addMapping(AddChoreActivity.class,
                R.drawable.ic_launcher_foreground);
        im.addMapping(LoadImage.class, R.drawable.test_image);
        //im.addMapping(UserListing.class, R.drawable.ic_launcher_foreground);

        // the grid of icons
        GridView gridview = (GridView) findViewById(R.id.gridview); //The
        gridview.setAdapter(new IconAdapter(this, im));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(MainActivity.this,
                        im.getActivity(position));
                startActivity(i);
            }
        });
    }
}
