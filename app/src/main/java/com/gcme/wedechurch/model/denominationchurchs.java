package com.gcme.wedechurch.model;


import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by kzone on 5/8/2017.
 */

public class denominationchurchs extends SugarRecord {

    @Unique
    String churchphone;
    String churchwebsite;
    String churchmail;
    String churchlongitude;
    String churchlatitude;
    String nameChurchs,location,denochurchimageUrl;
    private String mId;


    public denominationchurchs() {
    }

    public denominationchurchs(String id, String nameChurchs, String location, String denochurchimageUrl,
                               String churchphone,String churchwebsite,String churchmail,
                               String churchlongitude,String churchlatitude) {
        this.mId = id;
        this.nameChurchs = nameChurchs;
        this.location = location;
        this.denochurchimageUrl = denochurchimageUrl;
        this.churchphone = churchphone;
        this.churchwebsite = churchwebsite;
        this.churchmail = churchmail;
        this.churchlongitude = churchlongitude;
        this.churchlatitude = churchlatitude;
    }
    public String getChurchphone() {
        return churchphone;
    }

    public void setChurchphone(String churchphone) {
        this.churchphone = churchphone;
    }

    public String getChurchwebsite() {
        return churchwebsite;
    }

    public void setChurchwebsite(String churchwebsite) {
        this.churchwebsite = churchwebsite;
    }

    public String getChurchmail() {
        return churchmail;
    }

    public void setChurchmail(String churchmail) {
        this.churchmail = churchmail;
    }

    public String getChurchlongitude() {
        return churchlongitude;
    }

    public void setChurchlongitude(String churchlongitude) {
        this.churchlongitude = churchlongitude;
    }

    public String getChurchlatitude() {
        return churchlatitude;
    }

    public void setChurchlatitude(String churchlatitude) {
        this.churchlatitude = churchlatitude;
    }
    public String getchId() {
        return mId;
    }

    public void setchId(String id) {
        mId = id;
    }

    public String getNameChurchs() {
        return nameChurchs;
    }

    public void setNameChurchs(String nameChurchs) {
        this.nameChurchs = nameChurchs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDenochurchimageUrl() {
        return denochurchimageUrl;
    }

    public void setDenochurchimageUrl(String denochurchimageUrl) {
        this.denochurchimageUrl = denochurchimageUrl;
    }







}
