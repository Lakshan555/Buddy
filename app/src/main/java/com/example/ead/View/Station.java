package com.example.ead.View;

public class Station {
    private String ownerId;
    private String name;
    private String registerNo;
    private String StationNo;
    public Station(){}

    public Station(String ownerId, String name, String registerNo, String stationNo) {
        this.ownerId = ownerId;
        this.name = name;
        this.registerNo = registerNo;
        StationNo = stationNo;
    }

    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegisterNo() {
        return registerNo;
    }
    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getStationNo() {
        return StationNo;
    }

    public void setStationNo(String stationNo) {
        StationNo = stationNo;
    }
}