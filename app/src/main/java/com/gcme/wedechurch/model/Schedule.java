package com.gcme.wedechurch.model;

import com.orm.SugarRecord;

/**
 * Created by kzone on 3/6/2017.
 */

public class Schedule extends SugarRecord<Schedule> {

    private int sirid ;
    private String churchId;

    public int getSirid() {
        return sirid;
    }

    public void setSirid(int sirid) {
        this.sirid = sirid;
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

    public String getRedundancy() {
        return redundancy;
    }

    public void setRedundancy(String redundancy) {
        this.redundancy = redundancy;
    }

    public String getScheduleCategoryId() {
        return scheduleCategoryId;
    }

    public void setScheduleCategoryId(String scheduleCategoryId) {
        this.scheduleCategoryId = scheduleCategoryId;
    }

    private String date;
    private String startingTime;
    private String endTime;
    private String redundancy;
    private String scheduleCategoryId;


}
