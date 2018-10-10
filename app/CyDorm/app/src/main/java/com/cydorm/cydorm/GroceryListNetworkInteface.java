package com.cydorm.cydorm;

import java.util.ArrayList;
import java.util.List;

public interface GroceryListNetworkInteface {


    public int updateListItem(GroceryItem i);

    public int addListItem(GroceryItem i);

    public List<GroceryItem> getGroceryList();
}
