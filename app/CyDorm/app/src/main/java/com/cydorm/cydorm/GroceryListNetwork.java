package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

public class GroceryListNetwork {

    public GroceryListNetwork() {

    }

    public int test_connection() {
        return 0;
    }

    public int updateListItem(GroceryItem i) {
        return 0;
    }

    public int addListItem(GroceryItem i) {
        return 0;
    }

    public List<GroceryItem> getGroceryList() {
        List<GroceryItem> l = new ArrayList<>();

        l.add(new GroceryItem("Bananas"));
        l.add(new GroceryItem("Toilet Paper"));
        l.add(new GroceryItem("Tendies"));
        l.add(new GroceryItem("Paper Towels"));


        return l;
    }
}
