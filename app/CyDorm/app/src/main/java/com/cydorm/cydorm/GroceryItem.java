package com.cydorm.cydorm;

import org.json.JSONObject;

public class GroceryItem {
    private String item;
    private String itemID;
    private String authorFirst;
    private String authorLast;
    private String price;

    public GroceryItem(String name, String id, String first, String last,
                       String price) {
        this.item = name;
        this.itemID = id;
        this.authorFirst = first;
        this.authorLast = last;
        this.price = price;
    }

    public GroceryItem(GroceryItem clone) {
        this.item = clone.item;
        this.itemID = clone.itemID;
        this.authorFirst = clone.authorFirst;
        this.authorLast = clone.authorLast;
        this.price = clone.price;
    }

    public GroceryItem(JSONObject json) {
        try {
            this.item = json.getString("groceryItem");
            this.itemID = json.getString("id");
            this.authorFirst = "";
            this.authorLast = "";
            this.price = json.getString("groceryPrice");

        } catch (Exception e)  {
            e.printStackTrace();
        }

    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String i) {
        this.item = i;
    }

    public void setID(String id) {
        this.itemID = id;
    }

    public String getID() {
        return this.itemID;
    }

    public String getAuthorFirst() {return this.authorFirst;}

    public String getAuthorLast() {return this.authorLast;}

    public String getPrice() {return this.price;}

    @Override
    public String toString() {
        return this.item;
    }
}
