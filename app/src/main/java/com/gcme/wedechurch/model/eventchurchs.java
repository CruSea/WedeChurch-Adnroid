package com.gcme.wedechurch.model;

import java.io.Serializable;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;
/**
 * Created by kzone on 5/8/2017.
 */

public class eventchurchs extends SugarRecord {

    @Unique
    String nameevent;
    String eventtime;
    String eventdate;
    String eventcategory;
    String eventimageurl;
    String eventdescription;
    String eventlocation;
    String eventlongitude;
    String eventlatitude;
    private long mId;

    public eventchurchs() {
    }


    public eventchurchs(long id,String nameevent, String eventtime, String eventdate, String eventcategory, String eventimageurl,
                        String description,String eventlocation,String eventlongitude,String eventlatitude ) {
        this.mId = id;
        this.nameevent = nameevent;
        this.eventtime = eventtime;
        this.eventdate = eventdate;
        this.eventcategory = eventcategory;
        this.eventimageurl=eventimageurl;
        this.eventdescription = description;
        this.eventlocation = eventlocation;
        this.eventlocation = eventlocation;
        this.eventlatitude=eventlatitude;
    }



    public String getEventdescription() {
        return eventdescription;
    }

    public void setEventdescription(String eventdescription) {
        this.eventdescription = eventdescription;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
    }

    public String getEventlongitude() {
        return eventlongitude;
    }

    public void setEventlongitude(String eventlongitude) {
        this.eventlongitude = eventlongitude;
    }

    public String getEventlatitude() {
        return eventlatitude;
    }

    public void setEventlatitude(String eventlatitude) {
        this.eventlatitude = eventlatitude;
    }

    public String getNameevent() {
        return nameevent;
    }

    public void setNameevent(String nameevent) {
        this.nameevent = nameevent;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventcategory() {
        return eventcategory;
    }

    public void setEventcategory(String eventcategory) {
        this.eventcategory = eventcategory;
    }
    public String getEventimageurl() {
        return eventimageurl;
    }

    public void setEventimageurl(String eventimageurl) {
        this.eventimageurl = eventimageurl;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

}
