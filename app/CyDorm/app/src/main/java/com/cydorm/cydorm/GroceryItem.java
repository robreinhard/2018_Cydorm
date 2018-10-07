package com.cydorm.cydorm;

public class GroceryItem {
    private String item;
    private String itemID;

    public GroceryItem(String i) {
        this.item = i;
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

    @Override
    public String toString() {
        return this.item;
    }
}
