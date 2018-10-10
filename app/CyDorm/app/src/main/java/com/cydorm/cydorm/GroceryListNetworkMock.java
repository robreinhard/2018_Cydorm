package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

public class GroceryListNetworkMock implements GroceryListNetworkInteface {

    public GroceryListNetworkMock() {

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

        return l;
    }
}
