package com.cydorm.cydorm;

import org.json.JSONObject;
/**
 * This class stores all of the relevant information for individual grocery items
 */
public class GroceryItem {
    private String item;
    private String itemID;
    private String authorFirst;
    private String authorLast;
    private String price;

    /**
     * @param String name - name of grocery
     * @param String id - id number of grocery
     * @param String first - First name of user who added item
     * @param String last - Last name of user who added item
     * @param String price - price of grocery
     */
    public GroceryItem(String name, String id, String first, String last,
                       String price) {
        this.item = name;
        this.itemID = id;
        this.authorFirst = first;
        this.authorLast = last;
        this.price = price;
    }

    /**
     * @param GroceryItem clone - item to be cloned
     * This method clones a grocery item
     */
    public GroceryItem(GroceryItem clone) {
        this.item = clone.item;
        this.itemID = clone.itemID;
        this.authorFirst = clone.authorFirst;
        this.authorLast = clone.authorLast;
        this.price = clone.price;
    }

    /**
     * This method parses a json object into a grocery item
     * @param JSONObject json - json object to be parsed
     */
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

    /**
     * @return String item - returns grocery item name
     */
    public String getItem() {
        return this.item;
    }

    /**
     * @param String i - name to be set for item
     */
    public void setItem(String i) {
        this.item = i;
    }

    /**
     * @param String id - id to be set for item
     */
    public void setID(String id) {
        this.itemID = id;
    }

    /**
     * @return String ID - returns item ID number
     */
    public String getID() {
        return this.itemID;
    }

    /**
     * @return String first - returns first name of grocery item creator
     */
    public String getAuthorFirst() {return this.authorFirst;}

     /**
     * @return String last - returns last name of grocery item creator
     */
    public String getAuthorLast() {return this.authorLast;}

     /**
     * @return String price -  returns price of grocery item
     */
    public String getPrice() {return this.price;}

    /**
     * Standard toString function - returns item name
     */
    @Override
    public String toString() {
        return this.item;
    }
}
