package com.gcme.wedechurch.model;

import com.orm.SugarRecord;

/**
 * Created by kzone on 5/8/2017.
 */

public class denominationchurchs extends SugarRecord<denominationchurchs> {


    public denominationchurchs(long id, String nameChurchs, String location, String denochurchimageUrl) {
        this.mId = id;
        this.nameChurchs = nameChurchs;
        this.location = location;
        this.denochurchimageUrl = denochurchimageUrl;
    }

    public long getchId() {
        return mId;
    }

    public void setchId(long id) {
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



    String nameChurchs,location,denochurchimageUrl;
    private long mId;



}
