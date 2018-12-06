package com.cydorm.cydorm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

>>>>>>> e649b40081c2a4e39de9cf37cc9ae10368b85dfc

public class MainActivity extends AppCompatActivity {
    //private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~THE java request is cat");
        ServerSession session = new ServerSession("test", "test", this);

        //Setup the IconMappingScheme
        final IconMapping im = new IconMapping();


        //If you want to add a new activity to the home screen, create the
        // activity and add it to the below lines
        im.addMapping(GroceryManagerActivity.class, R.drawable.receipt_icon);
        im.addMapping(ChoreListActivity.class,
                R.drawable.chore_icon);
        im.addMapping(LoadImage.class, R.drawable.scan_icon);
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
