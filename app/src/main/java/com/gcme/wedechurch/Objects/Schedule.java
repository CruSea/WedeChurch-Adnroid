package com.gcme.wedechurch.Objects;

/**
 * Created by kzone on 3/6/2017.
 */

public class Schedule {

    private int id ;
    private String churchId;
    private String date;
    private String startingTime;
    private String endTime;


    public Schedule(){

    }

    public String getRedundancy() {
        return redundancy;
    }

    public void setRedundancy(String redundancy) {
        this.redundancy = redundancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChurchId() {
        return churchId;
    }

    public void setChurchId(String churchId) {
        this.churchId = churchId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getScheduleCategoryId() {
        return scheduleCategoryId;
    }

    public void setScheduleCategoryId(String scheduleCategoryId) {
        this.scheduleCategoryId = scheduleCategoryId;
    }

    private String redundancy;
    private String scheduleCategoryId;


}
