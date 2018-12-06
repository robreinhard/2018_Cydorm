package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

/** A no longer used interface that would allow the mocking of network functions*/
public interface GroceryListNetworkInteface {


    public int updateListItem(GroceryItem i);

    public int addListItem(GroceryItem i);

    public List<GroceryItem> getGroceryList();
}
