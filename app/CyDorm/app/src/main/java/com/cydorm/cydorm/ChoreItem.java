package com.cydorm.cydorm;

import org.json.JSONObject;

public class ChoreItem {

    private String name;
    private int day;
    private int month;
    private int year;
    private String id;

    public ChoreItem(JSONObject jo) {
        try {
            this.name = jo.get("cItem").toString();
            this.day = Integer.valueOf(jo.get("date").toString());
            this.month = Integer.valueOf(jo.get("month").toString());
            this.month = Integer.valueOf(jo.get("year").toString());
            this.id = jo.get("studentID").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ChoreItem(String name, int day, int month, int year, String id) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.id = id;
    }

    public ChoreItem(ChoreItem ci) {
        this.name = ci.name;
        this.day = ci.day;
        this.month = ci.month;
        this.id = ci.id;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getID() {
        return id;
    }
}
