package com.cydorm.cydorm;

public class GroceryItem {
    private String item;

    public GroceryItem(String i) {
        this.item = i;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String i) {
        this.item = i;
    }

    @Override
    public String toString() {
        return this.item;
    }
}
