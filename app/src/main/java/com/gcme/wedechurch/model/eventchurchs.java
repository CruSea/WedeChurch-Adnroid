package com.gcme.wedechurch.model;

import com.orm.SugarRecord;

/**
 * Created by kzone on 5/8/2017.
 */

public class eventchurchs extends SugarRecord<eventchurchs> {

    String nameevent;
    String eventtime;
    String eventdate;
    String eventcategory;
    String eventimageurl;



    private long mId;



    public eventchurchs(long id,String nameevent, String eventtime, String eventdate, String eventcategory, String eventimageurl) {
        this.mId = id;
        this.nameevent = nameevent;
        this.eventtime = eventtime;
        this.eventdate = eventdate;
        this.eventcategory = eventcategory;
        this.eventimageurl=eventimageurl;
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
