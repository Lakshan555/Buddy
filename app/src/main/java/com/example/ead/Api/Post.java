package com.example.ead.Api;

import java.util.ArrayList;

public class Post {
//    _id":"63595ecee54986c80d8fc6b8","owner_id":"63595ecee54986c80d8fc6b6","register_no":"123","fuel_station_name
    private int id ;
    private String name;
    ArrayList fuel_details,arrived_time;

    public Post(int id, String name, ArrayList fuel_details, ArrayList arrived_time) {
        this.id = id;
        this.name = name;
        this.fuel_details = fuel_details;
        this.arrived_time = arrived_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getFuel_details() {
        return fuel_details;
    }

    public void setFuel_details(ArrayList fuel_details) {
        this.fuel_details = fuel_details;
    }

    public ArrayList getArrived_time() {
        return arrived_time;
    }

    public void setArrived_time(ArrayList arrived_time) {
        this.arrived_time = arrived_time;
    }
}
