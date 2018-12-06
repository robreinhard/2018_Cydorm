package com.cydorm.cydorm;

import org.json.JSONObject;

/**
 * Represents a Chore for the chore list
 *
 * @author Malcolm Boyd
 **/
public class ChoreItem {

    private String name;
    private int day;
    private int month;
    private int year;
    private String id;

    /**
     * Creates a chore item parsed from a json object. 
     *
     * The object must have the fieldsL
     *  - cItem The name of the item
     *  - date The day of the month the item was added
     *  - month The month ithe item was added
     *  - studentID The id of the student that added it
     *
     * @param JSONObject The json to parse into the chore
     */
    public ChoreItem(JSONObject jo) {
        try {
            this.name = jo.get("cItem").toString();
            this.day = Integer.valueOf(jo.get("date").toString());
            this.month = Integer.valueOf(jo.get("month").toString());
            this.year = Integer.valueOf(jo.get("year").toString());
            this.id = jo.get("studentID").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Create a chore item with the passed in information
     *
     * @param String name The name of the chore
     * @param int day Day of the month the chore was created
     * @param int month The month the chore was created
     * @param int year The year the chore was created
     * @param String The id of the creator the chore was created
     */
    public ChoreItem(String name, int day, int month, int year, String id) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.id = id;
    }

    /** 
     * A copy constructor that copies the passed item.
     *
     * @param ChoreItem ci The chore item to copy
     **/
    public ChoreItem(ChoreItem ci) {
        this.name = ci.name;
        this.day = ci.day;
        this.month = ci.month;
        this.id = ci.id;
    }

    /**
     * @return String The name of the chore
     */
    public String getName() {
        return name;
    }

    /**
     * @return int Date of the month of chore creation
     **/
    public int getDay() {
        return day;
    }

    /** 
     * @return int What month the chore was created
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return int Year created
     **/
    public int getYear() {
        return year;
    }

    /**
     * @return String Creator ID
     */
    public String getID() {
        return id;
    }

    /**
     * Creates a string from the chore, this is the name
     * @return String The name of the chore
     **/
    @Override
    public String toString() {
        return this.name;
    }

}
