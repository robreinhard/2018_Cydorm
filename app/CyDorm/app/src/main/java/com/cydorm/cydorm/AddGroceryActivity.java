package com.cydorm.cydorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.techdew.stomplibrary.StompClient;

public class AddGroceryActivity extends AppCompatActivity {
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);

        EditText nameText = (EditText) findViewById(R.id.groceryEditText);
        EditText priceText = (EditText) findViewById(R.id.priceEditText);
        Button addBut = (Button) findViewById(R.id.addGroceryButton);
        Button removeBut = (Button) findViewById(R.id.removeGroceryButton);

        String importString = getIntent().getStringExtra("name");
        String importPrice = getIntent().getStringExtra("price");
        String importID = getIntent().getStringExtra("id");
        if(importString != null && importPrice != null && importID != null) {
            nameText.setText(getIntent().getStringExtra("name"));
            priceText.setText(getIntent().getStringExtra("price"));
            this.isEditing = true;
        }
        if(importString != null && importPrice != null && importID != null && importID.equals("")) {
            nameText.setText(getIntent().getStringExtra("name"));
            priceText.setText(getIntent().getStringExtra("price"));
            this.isEditing = false;
        }

        StompConnection sc = new StompConnection("8B8CD50EF9319D75C54BB3489A8810D3");

        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEditing) {
                    //Remove the item first
                    sc.sc.send("/deleteGroceryItem", String.format("{\"netID\":\"%s\" , " +
                            "\"grocery_id\" : \" %s \" }", ServerSessionSingleton.getInstance().getUser(), importID)).subscribe();
                }
                String is = String.format("{ \"groceryItem\": \"%s\", " +
                                "\"groceryPrice\" : \"%s\"," +
                                "\"approved\":\"F\", \"studentID\":\"%s\"}", nameText.getText().toString(), priceText.getText().toString()
                , ServerSessionSingleton.getInstance().getUser());
                sc.sc.send("/addGroceryItem", is).subscribe();
                finish();
            }
        });
        removeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEditing) {
                    //Remove the item first
                    sc.sc.send("/deleteGroceryItem", String.format("{\"netID\":\"%s\" , " +
                            "\"grocery_id\" : \" %s \" }", ServerSessionSingleton.getInstance().getUser(), importID)).subscribe();
                }
                finish();
            }
        });


    }
}
